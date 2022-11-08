package pl.edu.pw.infstos.szsdsr.charges.passings.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.PassingCharge;
import pl.edu.pw.infstos.szsdsr.charges.passings.repository.PassingChargeRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.ChargeDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.PassingChargeDTO;

import java.util.List;
import java.util.UUID;

@Service
public class PassingChargeService {
    private final PassingChargeRepository passingChargeRepo;
    private final ObjectMapper objectMapper;

    public PassingChargeService(PassingChargeRepository passingChargeRepo, ObjectMapper objectMapper) {
        this.passingChargeRepo = passingChargeRepo;
        this.objectMapper = objectMapper;
    }

    public List<PassingChargeDTO> getNotPaidPassingCharges(UUID uuid) {
        return passingChargeRepo.findNotPaidCharges(uuid)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private PassingChargeDTO toDTO(PassingCharge passingCharge) {
        return objectMapper.convertValue(passingCharge, PassingChargeDTO.class);
    }

}
