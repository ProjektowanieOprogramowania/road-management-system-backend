package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.AuctionsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionDTO;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services.AuctionService;

import java.util.List;

@RestController
public class AuctionsApiController implements AuctionsApi {
    private final AuctionService auctionService;

    public AuctionsApiController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @Override
    public ResponseEntity<AuctionDTO> createAuction(AuctionDTO auctionDTO) {
        return ResponseEntity.ok(auctionService.createAuction(auctionDTO));
    }

    @Override
    public ResponseEntity<List<AuctionDTO>> getAllAuctions() {
        return ResponseEntity.ok(auctionService.getAllAuctions());
    }

    @Override
    public ResponseEntity<AuctionDTO> getAuctionById(Long auctionId) {
        return ResponseEntity.ok(auctionService.getAuctionById(auctionId));
    }

    @Override
    public ResponseEntity<AuctionDTO> updateAuction(AuctionDTO auctionDTO) {
        return ResponseEntity.ok(auctionService.updateAuction(auctionDTO));
    }

}
