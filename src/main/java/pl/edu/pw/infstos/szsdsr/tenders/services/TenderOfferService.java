package pl.edu.pw.infstos.szsdsr.tenders.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.TenderDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.TenderOfferDTO;
import pl.edu.pw.infstos.szsdsr.tenders.domain.TenderOffer;
import pl.edu.pw.infstos.szsdsr.tenders.repo.TenderOfferRepo;

@Service
public class TenderOfferService {
    private final TenderOfferRepo tenderOfferRepo;
    private final ObjectMapper mapper;

    public TenderOfferService(TenderOfferRepo tenderOfferRepo, ObjectMapper mapper) {
        this.tenderOfferRepo = tenderOfferRepo;
        this.mapper = mapper;
    }

    public TenderOfferDTO createOffer(TenderOfferDTO tenderOfferDTO) {
        var offer = mapper.convertValue(tenderOfferDTO, TenderOffer.class);
        offer = tenderOfferRepo.save(offer);
        return mapper.convertValue(offer, TenderOfferDTO.class);
    }

}
