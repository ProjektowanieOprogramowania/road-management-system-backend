package pl.edu.pw.infstos.szsdsr.tolls.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;

@RestController
public class SubscriptionApiController implements SubscriptionsApi {

    public SubscriptionApiController() {
    }
    @Override
    public ResponseEntity<SubscriptionDTO> getSubscriptionInfo() {

        //return (ResponseEntity<SubscriptionDTO>) tollService.getSubscriptionInfo();
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /*@Override
    public ResponseEntity purchaseSubsription(SubscriptionDTO subscriptionDTO) {
        try{
            tollService.purchaseSubscription(subscriptionDTO);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }*/

}
