package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionDTO;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.Auction;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.repo.AuctionRepo;

import java.util.List;

@Service
public class AuctionService {
    private final AuctionRepo auctionRepo;
    private final ObjectMapper mapper;

    public AuctionService(AuctionRepo auctionRepo, ObjectMapper mapper) {
        this.auctionRepo = auctionRepo;
        this.mapper = mapper;
    }

    public AuctionDTO createAuction(AuctionDTO auctionDTO) {
        var auction = mapper.convertValue(auctionDTO, Auction.class);
        auction = auctionRepo.save(auction);
        return mapper.convertValue(auction, AuctionDTO.class);
    }

    public List<AuctionDTO> getAllAuctions() {
        return auctionRepo.findAll()
                .stream()
                .map(t -> mapper.convertValue(t, AuctionDTO.class))
                .toList();
    }

    public AuctionDTO getAuctionById(Long auctionId) {
        var auction = auctionRepo.findById(auctionId).orElseThrow();
        return mapper.convertValue(auction, AuctionDTO.class);
    }

    public AuctionDTO updateAuction(AuctionDTO auctionDTO) {
        var auction = mapper.convertValue(auctionDTO, Auction.class);
        auction = auctionRepo.save(auction);
        return mapper.convertValue(auction, AuctionDTO.class);
    }

    public AuctionDTO closeAuction(AuctionDTO auctionDTO) {
        auctionDTO.setIsOpen(false);
        var auction = mapper.convertValue(auctionDTO, Auction.class);
        auction = auctionRepo.save(auction);
        return mapper.convertValue(auction, AuctionDTO.class);
    }


}
