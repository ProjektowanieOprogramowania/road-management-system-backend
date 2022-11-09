package pl.edu.pw.infstos.szsdsr.charges.passings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.core.service.ChargeService;
import pl.edu.pw.infstos.szsdsr.charges.core.service.PaymentService;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.SubscriptionService;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.PaymentDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.PaymentMethodDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SubscriptionApiController implements SubscriptionsApi {
    private final SubscriptionService subscriptionService;
    private final PaymentService paymentService;
    private final ChargeService chargeService;

    public SubscriptionApiController(@Autowired SubscriptionService subscriptionService,
                                     @Autowired PaymentService paymentService,
                                     @Autowired ChargeService chargeService) {
        this.subscriptionService = subscriptionService;
        this.paymentService = paymentService;
        this.chargeService = chargeService;
    }

    @Override
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(UUID userUUID) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userUUID));
    }

    @Override
    public ResponseEntity<String> paySubscription(Long subscriptionId) {
        Optional<SubscriptionDTO> maybeSubscription = subscriptionService.getSubscription(subscriptionId);

        if (maybeSubscription.isPresent()) {
            SubscriptionDTO subscription = maybeSubscription.get();

            if(subscription.getCharge().getPaid()) {
                return ResponseEntity.badRequest().body("Already paid");
            }

            PaymentDTO payment = new PaymentDTO();
            payment.setAmount(subscription.getCharge().getAmount());
            payment.setPaymentMethod(String.valueOf(PaymentMethodDTO.BLIK));
            payment.setDateTime(LocalDateTime.now());
            payment = paymentService.addPayment(payment);

            subscription.getCharge().setPaid(true);
            subscription.getCharge().setPayment(payment);
            chargeService.updateCharge(subscription.getCharge());

            return ResponseEntity.ok("/charges/payment/" + payment.getId());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
