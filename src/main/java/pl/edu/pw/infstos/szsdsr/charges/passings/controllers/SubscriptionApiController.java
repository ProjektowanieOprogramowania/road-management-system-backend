package pl.edu.pw.infstos.szsdsr.charges.passings.controllers;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.core.service.ChargeService;
import pl.edu.pw.infstos.szsdsr.charges.core.service.PaymentService;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.SubscriptionService;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.*;
import pl.edu.pw.infstos.szsdsr.roads.domain.Road;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadService;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
public class SubscriptionApiController implements SubscriptionsApi {
    private final SubscriptionService subscriptionService;
    private final PaymentService paymentService;
    private final ChargeService chargeService;
    private final RoadService roadService;

    public SubscriptionApiController(@Autowired SubscriptionService subscriptionService,
                                     @Autowired PaymentService paymentService,
                                     @Autowired ChargeService chargeService,
                                     @Autowired RoadService roadService) {
        this.subscriptionService = subscriptionService;
        this.paymentService = paymentService;
        this.chargeService = chargeService;
        this.roadService = roadService;
    }

    @Override
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(UUID userUUID) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userUUID));
    }


    @Override
    public ResponseEntity<SubscriptionDTO> buySubscription(String paymentMethod, SubscriptionDTO subscriptionDTO) {
        if (!EnumUtils.isValidEnum(PaymentMethodDTO.class, paymentMethod.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (subscriptionDTO.getRoadsIds().isEmpty()
        || subscriptionDTO.getSubscriptionFrom().isAfter(subscriptionDTO.getSubscriptionTo())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> ids = subscriptionDTO.getRoadsIds().stream().map(Long::valueOf).toList();
        List<Road> roads = roadService.findByIds(ids);

        if (roads.size() != ids.size()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        double days = DAYS.between(subscriptionDTO.getSubscriptionFrom(), subscriptionDTO.getSubscriptionTo());
        double amount = roads.stream()
                .map(r -> r.getSubscriptionPriceForOneDay() * days)
                .mapToDouble(Double::doubleValue)
                .sum();

        amount = Math.round(amount * 100.0) / 100.0;

        PaymentDTO payment = new PaymentDTO();
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setDateTime(OffsetDateTime.now());
        payment = paymentService.addPayment(payment);

        ChargeDTO chargeDTO = new ChargeDTO();
        chargeDTO.setChargeType(String.valueOf(ChargeTypeDTO.SUBSCRIPTION_CHARGE));

        List<String> roadsNames = roadService.findNamesByIds(ids);

        String roadsString = roadsNames
                .stream()
                .collect(Collectors.joining("/", "[", "]"));
        chargeDTO.setDescription("Opłata za abonament " + roadsString);
        chargeDTO.setPaid(true);
        chargeDTO.setAmount(amount);
        chargeDTO.setUserId(subscriptionDTO.getSubscriberId());
        chargeDTO.setPayment(payment);
        chargeDTO = chargeService.addCharge(chargeDTO);

        subscriptionDTO.setCharge(chargeDTO);
        subscriptionDTO = subscriptionService.addSubscription(subscriptionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionDTO);
    }
}
