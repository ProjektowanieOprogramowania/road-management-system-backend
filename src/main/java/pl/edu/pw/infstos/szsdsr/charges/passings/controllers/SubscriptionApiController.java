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
import pl.edu.pw.infstos.szsdsr.road.services.RoadService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        if (subscriptionDTO.getRoadsIds().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        double amount = subscriptionDTO.getRoadsIds().size() * 23.99;

        PaymentDTO payment = new PaymentDTO();
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setDateTime(LocalDateTime.now());
        payment = paymentService.addPayment(payment);

        ChargeDTO chargeDTO = new ChargeDTO();
        chargeDTO.setChargeType(String.valueOf(ChargeTypeDTO.SUBSCRIPTION_CHARGE));
        List<Long> ids = subscriptionDTO.getRoadsIds().stream().map(Long::valueOf).toList();
        List<String> roadsNames = roadService.findNamesByIds(ids);

        if(roadsNames.size() != ids.size()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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
