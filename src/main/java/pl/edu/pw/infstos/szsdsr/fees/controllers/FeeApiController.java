package pl.edu.pw.infstos.szsdsr.fees.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import pl.edu.pw.infstos.szsdsr.fees.services.FeeService;
import pl.edu.pw.infstos.szsdsr.generated.api.FeesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.FeeDTO;
import pl.edu.pw.infstos.szsdsr.tolls.services.TollService;

import java.util.List;
import java.util.UUID;

public class FeeApiController implements FeesApi {

    private final FeeService feeService;
    public FeeApiController(@Autowired FeeService feeService) {
        this.feeService = feeService;
    }
    @Override
    public ResponseEntity<List<FeeDTO>> getUsersFee(UUID userUUID) {

        return (ResponseEntity<List<FeeDTO>>) feeService.getUsersFee(userUUID);
    }

}
