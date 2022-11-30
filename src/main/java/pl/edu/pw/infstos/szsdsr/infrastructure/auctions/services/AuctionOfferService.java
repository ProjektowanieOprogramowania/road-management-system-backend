package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionOfferDTO;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.AuctionOffer;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.repo.AuctionOfferRepo;

import java.util.List;

@Service
public class AuctionOfferService {
    private final AuctionOfferRepo auctionOfferRepo;
    private final ObjectMapper mapper;

    public AuctionOfferService(AuctionOfferRepo auctionOfferRepo, ObjectMapper mapper) {
        this.auctionOfferRepo = auctionOfferRepo;
        this.mapper = mapper;
    }

    public AuctionOfferDTO createOffer(AuctionOfferDTO auctionOfferDTO) {
        var offer = mapper.convertValue(auctionOfferDTO, AuctionOffer.class);
        offer = auctionOfferRepo.save(offer);
        return mapper.convertValue(offer, AuctionOfferDTO.class);
    }

    public List<AuctionOfferDTO> getAllOffers() {
        return auctionOfferRepo.findAll()
                .stream()
                .map(t -> mapper.convertValue(t, AuctionOfferDTO.class))
                .toList();
    }

}
