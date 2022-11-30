package pl.edu.pw.infstos.szsdsr.tenders.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.TendersApi;
import pl.edu.pw.infstos.szsdsr.generated.models.TenderDTO;
import pl.edu.pw.infstos.szsdsr.tenders.services.TenderService;

import java.util.List;

@RestController
public class TendersApiController implements TendersApi {
    private final TenderService tenderService;

    public TendersApiController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @Override
    public ResponseEntity<TenderDTO> createTender(TenderDTO tenderDTO) {
        return ResponseEntity.ok(tenderService.createTender(tenderDTO));
    }

    @Override
    public ResponseEntity<List<TenderDTO>> getAllTenders() {
        return ResponseEntity.ok(tenderService.getAllTenders());
    }

    @Override
    public ResponseEntity<TenderDTO> getTenderById(Long tenderId) {
        return ResponseEntity.ok(tenderService.getTenderById(tenderId));
    }

    @Override
    public ResponseEntity<TenderDTO> updateTender(TenderDTO tenderDTO) {
        return ResponseEntity.ok(tenderService.updateTender(tenderDTO));
    }

}
