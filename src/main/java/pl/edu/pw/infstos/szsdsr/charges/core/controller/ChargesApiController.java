package pl.edu.pw.infstos.szsdsr.charges.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.core.service.ChargeService;
import pl.edu.pw.infstos.szsdsr.charges.core.service.PaymentService;
import pl.edu.pw.infstos.szsdsr.generated.api.ChargesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.ChargeDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.PaymentDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ChargesApiController implements ChargesApi {

    private final ChargeService chargeService;
    private final PaymentService paymentService;

    public ChargesApiController(@Autowired ChargeService chargeService,
                                @Autowired PaymentService paymentService) {
        this.chargeService = chargeService;
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<List<ChargeDTO>> getUsersCharges(UUID userUUID) {
        return ResponseEntity.ok(chargeService.getUserCharges(userUUID));
    }

    @Override
    public ResponseEntity<PaymentDTO> getPayment(BigDecimal paymentId) {
        Optional<PaymentDTO> maybePayment = paymentService.getPayment(paymentId.longValue());
        if (maybePayment.isPresent()) {
            return ResponseEntity.ok(maybePayment.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
