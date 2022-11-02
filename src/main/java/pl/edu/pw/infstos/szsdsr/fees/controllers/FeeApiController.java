package pl.edu.pw.infstos.szsdsr.fees.controllers;

import org.springframework.http.ResponseEntity;
import pl.edu.pw.infstos.szsdsr.generated.api.FeesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.FeeDTO;

import java.util.List;
import java.util.UUID;

public class FeeApiController implements FeesApi {

    @Override
    public ResponseEntity<List<FeeDTO>> getUsersFee(UUID userUUID) {
        return FeesApi.super.getUsersFee(userUUID);
    }

}
