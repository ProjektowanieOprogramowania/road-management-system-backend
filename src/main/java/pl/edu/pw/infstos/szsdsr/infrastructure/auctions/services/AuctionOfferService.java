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
import java.util.stream.Collectors;

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
        auctionOffers = auctionOffers.stream().filter(ao -> ao.getScore() != null).collect(Collectors.toList());

        if (auctionOffers.isEmpty()) {
            return null;
        }

        AuctionOffer winningOffer = auctionOffers.stream().max(Comparator.comparing(AuctionOffer::getScore)).orElse(null);
        return mapper.convertValue(winningOffer, AuctionOfferDTO.class);
    }
}
