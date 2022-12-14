package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.AuctionOfferApi;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionOfferDTO;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services.AuctionOfferService;

import java.util.List;

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

    @Override
    public ResponseEntity<List<AuctionOfferDTO>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @Override
    public ResponseEntity<List<AuctionOfferDTO>> getAuctionOffers(Long auctionId) {
        return ResponseEntity.ok(offerService.getAuctionOffers(auctionId));
    }

    @Override
    public ResponseEntity<AuctionOfferDTO> updateOffer(AuctionOfferDTO auctionOfferDTO) {
        return ResponseEntity.ok(offerService.updateOffer(auctionOfferDTO));
    }

    @Override
    public ResponseEntity<AuctionOfferDTO> updateOfferScore(AuctionOfferDTO auctionOfferDTO) {
        return ResponseEntity.ok(offerService.updateOffer(auctionOfferDTO));
    }

    @Override
    public ResponseEntity<AuctionOfferDTO> getWinningOffer(Long auctionId) {
        return ResponseEntity.ok(offerService.getWinningOffer(auctionId));
    }

}
