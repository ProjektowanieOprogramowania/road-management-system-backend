package pl.edu.pw.infstos.szsdsr.tolls.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.TollApi;
import pl.edu.pw.infstos.szsdsr.generated.models.NotPayedTollDTO;

import java.util.List;
import java.util.UUID;

@RestController
public class TollApiController implements TollApi {

    @Override
    public ResponseEntity<List<NotPayedTollDTO>> getNotPaidTolls(UUID userUUID) {
        return TollApi.super.getNotPaidTolls(userUUID);
    }

    @Override
    public ResponseEntity<Object> payToll(Long tollId) {
        return TollApi.super.payToll(tollId);
    }

}
