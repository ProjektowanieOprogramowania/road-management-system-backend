package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.infstos.szsdsr.generated.models.AuctionOfferDTO;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.AuctionOffer;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.repo.AuctionOfferRepo;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;
import pl.edu.pw.infstos.szsdsr.users.repository.AppUserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuctionOfferService {
    private final AuctionOfferRepo auctionOfferRepo;
    private final AppUserRepository appUserRepository;
    private final ObjectMapper mapper;

    public AuctionOfferService(AuctionOfferRepo auctionOfferRepo, ObjectMapper mapper, AppUserRepository appUserRepository) {
        this.auctionOfferRepo = auctionOfferRepo;
        this.appUserRepository = appUserRepository;
        this.mapper = mapper;
    }

    public AuctionOfferDTO createOffer(AuctionOfferDTO auctionOfferDTO) {
        var offer = mapper.convertValue(auctionOfferDTO, AuctionOffer.class);
        Optional<AppUser> maybeUser = appUserRepository.findByUuid(offer.getUserId());
        if (maybeUser.isPresent()) {
            offer.setCompanyName(maybeUser.get().getCompanyName());
        }
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
        Optional<AppUser> maybeUser = appUserRepository.findByUuid(offer.getUserId());
        if (maybeUser.isPresent()) {
            offer.setCompanyName(maybeUser.get().getCompanyName());
        }
        offer = auctionOfferRepo.save(offer);
        return mapper.convertValue(offer, AuctionOfferDTO.class);
    }

    public AuctionOfferDTO getWinningOffer(Long auctionId) {
        List<AuctionOffer> auctionOffers = auctionOfferRepo.findByAuctionId(auctionId);

        if (auctionOffers.isEmpty()) {
            return null;
        }

        AuctionOffer winningOffer = Collections.max(auctionOffers, new Comparator() {
            public int compare(Object o1, Object o2) {
                AuctionOffer offer1 = (AuctionOffer) o1;
                AuctionOffer offer2 = (AuctionOffer) o2;
                Integer score1 = offer1.getScore();
                Integer score2 = offer2.getScore();
                if (score1 == null && score2 == null) {
                    return 0;
                } else if (score1 == null && score2 != null) {
                    return -1;
                } else if (score1 != null && score2 == null) {
                    return 1;
                }
                return offer1.getScore().compareTo(offer2.getScore());
            }
        });
        return mapper.convertValue(winningOffer, AuctionOfferDTO.class);
    }
}
