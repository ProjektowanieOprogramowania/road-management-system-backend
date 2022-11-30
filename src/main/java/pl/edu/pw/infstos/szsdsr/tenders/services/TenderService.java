package pl.edu.pw.infstos.szsdsr.tenders.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.TenderDTO;
import pl.edu.pw.infstos.szsdsr.tenders.domain.Tender;
import pl.edu.pw.infstos.szsdsr.tenders.repo.TenderRepo;

import java.util.List;

@Service
public class TenderService {
    private final TenderRepo tenderRepo;
    private final ObjectMapper mapper;

    public TenderService(TenderRepo tenderRepo, ObjectMapper mapper) {
        this.tenderRepo = tenderRepo;
        this.mapper = mapper;
    }

    public TenderDTO createTender(TenderDTO tenderDTO) {
        var tender = mapper.convertValue(tenderDTO, Tender.class);
        tender = tenderRepo.save(tender);
        return mapper.convertValue(tender, TenderDTO.class);
    }

    public List<TenderDTO> getAllTenders() {
        return tenderRepo.findAll()
                .stream()
                .map(t -> mapper.convertValue(t, TenderDTO.class))
                .toList();
    }

    public TenderDTO getTenderById(Long tenderId) {
        var tender = tenderRepo.findById(tenderId).orElseThrow();
        return mapper.convertValue(tender, TenderDTO.class);
    }

    public TenderDTO updateTender(TenderDTO tenderDTO) {
        var tender = mapper.convertValue(tenderDTO, Tender.class);
        tender = tenderRepo.save(tender);
        return mapper.convertValue(tender, TenderDTO.class);
    }


}
