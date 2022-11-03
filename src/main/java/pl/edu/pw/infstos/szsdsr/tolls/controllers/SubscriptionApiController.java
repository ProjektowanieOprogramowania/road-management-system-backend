package pl.edu.pw.infstos.szsdsr.tolls.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.SubscriptionApi;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionInfoDTO;
import pl.edu.pw.infstos.szsdsr.tolls.services.TollService;

import java.util.List;

@RestController
public class SubscriptionApiController implements SubscriptionApi {
    private final TollService tollService;

    public SubscriptionApiController(@Autowired TollService tollService) {
        this.tollService = tollService;
    }
    @Override
    public ResponseEntity<List<SubscriptionInfoDTO>> getSubscriptionInfo() {

        return (ResponseEntity<List<SubscriptionInfoDTO>>) tollService.getSubscriptionInfo();
    }

}
