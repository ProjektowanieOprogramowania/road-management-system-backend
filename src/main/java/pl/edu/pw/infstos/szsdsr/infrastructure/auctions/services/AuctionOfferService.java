package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionOfferDTO;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.Auction;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.AuctionOffer;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.repo.AuctionOfferRepo;

import java.util.Collections;
import java.util.Comparator;
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

    public List<AuctionOfferDTO> getAuctionOffers(Long auctionId) {
        return auctionOfferRepo.findByAuctionId(auctionId)
                .stream()
                .map(t -> mapper.convertValue(t, AuctionOfferDTO.class))
                .toList();
    }

    public AuctionOfferDTO updateOffer(AuctionOfferDTO auctionOfferDTO) {
        var offer = mapper.convertValue(auctionOfferDTO, AuctionOffer.class);
        offer = auctionOfferRepo.save(offer);
        return mapper.convertValue(offer, AuctionOfferDTO.class);
    }

    public AuctionOfferDTO getWinningOffer(Long auctionId) {
        List<AuctionOffer> auctionOffers = auctionOfferRepo.findByAuctionId(auctionId);

        if(auctionOffers.isEmpty()) {
            return null;
        }

        AuctionOffer winningOffer = Collections.max(auctionOffers, new Comparator() {
            public int compare(Object o1,  Object o2) {
                AuctionOffer offer1 = (AuctionOffer) o1;
                AuctionOffer offer2 = (AuctionOffer) o2;
                Integer score1 = offer1.getScore();
                Integer score2 = offer2.getScore();
                if (score1 == null && score2 == null) {
                    return 0;
                } else if (score1 == null && score2 != null) {
                    return -1;
                } else if(score1 != null && score2 == null) {
                    return 1;
                }
                return offer1.getScore().compareTo(offer2.getScore());
            }
        });
        return mapper.convertValue(winningOffer, AuctionOfferDTO.class);
    }
}
