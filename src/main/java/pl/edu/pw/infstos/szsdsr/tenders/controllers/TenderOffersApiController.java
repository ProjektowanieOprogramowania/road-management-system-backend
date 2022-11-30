package pl.edu.pw.infstos.szsdsr.tenders.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.TenderOfferApi;
import pl.edu.pw.infstos.szsdsr.generated.models.TenderOfferDTO;
import pl.edu.pw.infstos.szsdsr.tenders.services.TenderOfferService;

import java.util.List;

@RestController
public class TenderOffersApiController implements TenderOfferApi {
    private final TenderOfferService offerService;

    public TenderOffersApiController(TenderOfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public ResponseEntity<TenderOfferDTO> createOffer(TenderOfferDTO tenderOfferDTO) {
        return ResponseEntity.ok(offerService.createOffer(tenderOfferDTO));
    }

}
