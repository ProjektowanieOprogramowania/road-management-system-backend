package pl.edu.pw.infstos.szsdsr.tolls.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.TollApi;
import pl.edu.pw.infstos.szsdsr.generated.models.NotPayedTollDTO;
import pl.edu.pw.infstos.szsdsr.tolls.services.TollService;

import java.util.List;
import java.util.UUID;

@RestController
public class TollApiController implements TollApi {
    private final TollService tollService;
    public TollApiController(@Autowired TollService tollService) {
        this.tollService = tollService;
    }
    @Override
    public ResponseEntity<List<NotPayedTollDTO>> getNotPaidTolls(UUID userUUID) {
        return (ResponseEntity<List<NotPayedTollDTO>>) tollService.getNotPaidTolls(userUUID);
    }

    @Override
    public ResponseEntity payToll(Long tollId) {
        try{
            tollService.payToll(tollId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
