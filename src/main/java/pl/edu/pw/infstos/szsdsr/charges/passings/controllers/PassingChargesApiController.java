package pl.edu.pw.infstos.szsdsr.charges.passings.controllers;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.charges.core.service.ChargeService;
import pl.edu.pw.infstos.szsdsr.charges.core.service.PaymentService;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.PassingChargeService;
import pl.edu.pw.infstos.szsdsr.generated.api.PassingChargesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.PassingChargeDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.PaymentDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.PaymentMethodDTO;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PassingChargesApiController implements PassingChargesApi {
    private final PassingChargeService passingChargeService;
    private final PaymentService paymentService;
    private final ChargeService chargeService;

    public PassingChargesApiController(@Autowired PassingChargeService passingChargeService,
                                       @Autowired PaymentService paymentService,
                                       @Autowired ChargeService chargeService) {
        this.passingChargeService = passingChargeService;
        this.paymentService = paymentService;
        this.chargeService = chargeService;
    }

    @Override
    public ResponseEntity<List<PassingChargeDTO>> getNotPaidPassingCharges(UUID userUUID) {
        return ResponseEntity.ok(passingChargeService.getNotPaidPassingCharges(userUUID));
    }

    @Override
    public ResponseEntity<String> payPassingCharge(Long passingChargeId, String paymentMethod) {
        Optional<PassingChargeDTO> maybePassingCharge = passingChargeService.getPassingCharge(passingChargeId);

        if (!EnumUtils.isValidEnum(PaymentMethodDTO.class, paymentMethod.toUpperCase())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (maybePassingCharge.isPresent()) {
            PassingChargeDTO passingCharge = maybePassingCharge.get();

            if(passingCharge.getCharge().getPaid()) {
                return ResponseEntity.badRequest().body("Already paid");
            }

            PaymentDTO payment = new PaymentDTO();
            payment.setAmount(passingCharge.getCharge().getAmount());
            payment.setPaymentMethod(paymentMethod);
            payment.setDateTime(OffsetDateTime.now());
            payment = paymentService.addPayment(payment);

            passingCharge.getCharge().setPaid(true);
            passingCharge.getCharge().setPayment(payment);
            chargeService.updateCharge(passingCharge.getCharge());

            return ResponseEntity.ok("/charges/payment/" + payment.getId());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
