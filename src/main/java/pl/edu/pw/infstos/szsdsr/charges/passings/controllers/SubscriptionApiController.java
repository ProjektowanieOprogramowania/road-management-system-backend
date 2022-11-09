package pl.edu.pw.infstos.szsdsr.charges.passings.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.SubscriptionService;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;

import java.util.List;
import java.util.UUID;

@RestController
public class SubscriptionApiController implements SubscriptionsApi {
    private final SubscriptionService subscriptionService;

    public SubscriptionApiController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(UUID userUUID) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userUUID));
    }

    @Override
    public ResponseEntity<Object> paySubscription() {
        return SubscriptionsApi.super.paySubscription();
    }
}
