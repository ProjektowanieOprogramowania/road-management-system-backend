package pl.edu.pw.infstos.szsdsr.auctions.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.AuctionOfferApi;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionOfferDTO;
import pl.edu.pw.infstos.szsdsr.auctions.services.AuctionOfferService;

@RestController
public class AuctionOffersApiController implements AuctionOfferApi {
    private final AuctionOfferService offerService;

    public AuctionOffersApiController(AuctionOfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public ResponseEntity<AuctionOfferDTO> createOffer(AuctionOfferDTO auctionOfferDTO) {
        return ResponseEntity.ok(offerService.createOffer(auctionOfferDTO));
    }

}
