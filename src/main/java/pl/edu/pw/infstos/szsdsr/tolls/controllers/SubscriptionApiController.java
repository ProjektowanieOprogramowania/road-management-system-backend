package pl.edu.pw.infstos.szsdsr.tolls.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionApi;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;

@RestController
public class SubscriptionApiController implements SubscriptionApi {

    @Override
    public ResponseEntity<SubscriptionDTO> getSubscriptionInfo() {
        return SubscriptionApi.super.getSubscriptionInfo();
    }

}
