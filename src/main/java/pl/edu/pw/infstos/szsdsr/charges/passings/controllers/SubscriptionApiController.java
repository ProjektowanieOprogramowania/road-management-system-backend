package pl.edu.pw.infstos.szsdsr.charges.passings.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.SubscriptionInfoService;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.SubscriptionService;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionInfoDTO;

import java.util.UUID;

@RestController
public class SubscriptionApiController implements SubscriptionsApi {
    private final SubscriptionInfoService subscriptionInfoService;
    private final SubscriptionService subscriptionService;

    public SubscriptionApiController(SubscriptionInfoService subscriptionInfoService, SubscriptionService subscriptionService) {
        this.subscriptionInfoService = subscriptionInfoService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public ResponseEntity<SubscriptionDTO> getSubscription(UUID userUUID) {
        return ResponseEntity.ok(subscriptionService.getUserSubscription(userUUID));
    }

    @Override
    public ResponseEntity<SubscriptionInfoDTO> getSubscriptionInfo() {
        return ResponseEntity.ok(subscriptionInfoService.getSubscriptionInfo());
    }

    @Override
    public ResponseEntity<Object> paySubscription() {
        return SubscriptionsApi.super.paySubscription();
    }
}
