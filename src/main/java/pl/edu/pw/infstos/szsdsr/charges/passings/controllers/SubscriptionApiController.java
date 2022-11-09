package pl.edu.pw.infstos.szsdsr.charges.passings.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.SubscriptionService;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionPaymentInfoDTO;

import java.util.List;
import java.util.UUID;

@RestController
public class SubscriptionApiController implements SubscriptionsApi {
    private final SubscriptionService subscriptionService;

    public SubscriptionApiController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public ResponseEntity<List<SubscriptionDTO>> getUserSubscriptions(UUID userUUID) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userUUID));
    }

    @Override
    public ResponseEntity<SubscriptionDTO> getSubscriptionSummary(SubscriptionPaymentInfoDTO subscriptionPaymentInfoDTO) {
        // TODO: zaimplementować
        return SubscriptionsApi.super.getSubscriptionSummary(subscriptionPaymentInfoDTO);
    }

    @Override
    public ResponseEntity<Object> paySubscription(SubscriptionPaymentInfoDTO subscriptionPaymentInfoDTO) {
        // TODO: zaimplementować
        return SubscriptionsApi.super.paySubscription(subscriptionPaymentInfoDTO);
    }
}
