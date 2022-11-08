package pl.edu.pw.infstos.szsdsr.charges.passings.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.PassingChargeService;
import pl.edu.pw.infstos.szsdsr.generated.api.PassingChargesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.PassingChargeDTO;
import pl.edu.pw.infstos.szsdsr.tolls.services.PassingChargeService2;

import java.util.List;
import java.util.UUID;

@RestController
public class PassingChargesApiController implements PassingChargesApi {
    private final PassingChargeService passingChargeService;
    public PassingChargesApiController(PassingChargeService passingChargeService) {
        this.passingChargeService = passingChargeService;
    }

    @Override
    public ResponseEntity<List<PassingChargeDTO>> getNotPaidPassingCharges(UUID userUUID) {
        return ResponseEntity.ok(passingChargeService.getNotPaidPassingCharges(userUUID));
    }

    @Override
    public ResponseEntity<Object> payPassingCharge(Long passingChargeId) {
        return PassingChargesApi.super.payPassingCharge(passingChargeId);
    }
}
