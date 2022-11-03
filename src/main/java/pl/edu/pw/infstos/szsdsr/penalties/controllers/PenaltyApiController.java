package pl.edu.pw.infstos.szsdsr.penalties.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.PenaltiesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.PenaltyDTO;
import pl.edu.pw.infstos.szsdsr.penalties.services.PenaltyService;

import java.util.List;
import java.util.UUID;

@RestController
public class PenaltyApiController implements PenaltiesApi {

    private final PenaltyService penaltyService;

    public PenaltyApiController(@Autowired PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @Override
    public ResponseEntity<List<PenaltyDTO>> getAllPenalties(UUID userUUID) {
        return ResponseEntity.ok(penaltyService.getAllPenalties(userUUID));
    }
}
