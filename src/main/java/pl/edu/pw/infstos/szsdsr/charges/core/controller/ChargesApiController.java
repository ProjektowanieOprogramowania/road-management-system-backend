package pl.edu.pw.infstos.szsdsr.charges.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.core.service.ChargeService;
import pl.edu.pw.infstos.szsdsr.generated.api.ChargesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.ChargeDTO;
import java.util.List;
import java.util.UUID;

@RestController
public class ChargesApiController implements ChargesApi {

    private final ChargeService chargeService;

    public ChargesApiController(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    @Override
    public ResponseEntity<List<ChargeDTO>> getUsersCharges(UUID userUUID) {
        return ResponseEntity.ok(chargeService.getUserCharges(userUUID));
    }
}
