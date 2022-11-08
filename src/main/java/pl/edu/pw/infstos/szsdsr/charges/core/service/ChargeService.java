package pl.edu.pw.infstos.szsdsr.charges.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;
import pl.edu.pw.infstos.szsdsr.charges.core.repo.ChargeRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.ChargeDTO;

import java.util.List;
import java.util.UUID;

@Service
public class ChargeService {
    private final ChargeRepository chargeRepo;
    private final ObjectMapper objectMapper;

    public ChargeService(ChargeRepository chargeRepo, ObjectMapper objectMapper) {
        this.chargeRepo = chargeRepo;
        this.objectMapper = objectMapper;
    }

    public List<ChargeDTO> getUserCharges(UUID userUUID) {
        return chargeRepo.findChargesByUser_Uuid(userUUID)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private ChargeDTO mapToDTO(Charge charge) {
        return objectMapper.convertValue(charge, ChargeDTO.class);
    }

    private Charge mapToDomain(ChargeDTO chargeDTO) {
        return objectMapper.convertValue(chargeDTO, Charge.class);
    }

}
