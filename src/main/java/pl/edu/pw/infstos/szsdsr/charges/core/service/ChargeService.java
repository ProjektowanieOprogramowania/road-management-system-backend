package pl.edu.pw.infstos.szsdsr.charges.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;
import pl.edu.pw.infstos.szsdsr.charges.core.repo.ChargeRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.ChargeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChargeService {
    private final ChargeRepository chargeRepository;
    private final ObjectMapper objectMapper;

    public ChargeService(ChargeRepository chargeRepository, ObjectMapper objectMapper) {
        this.chargeRepository = chargeRepository;
        this.objectMapper = objectMapper;
    }

    public List<ChargeDTO> getUserCharges(UUID userUUID) {
        return chargeRepository.findAllByUserId(userUUID)
                .stream()
                .map(this::chargeToDto)
                .toList();
    }

    public ChargeDTO addCharge(ChargeDTO chargeDto) {
        Charge charge = dtoToCharge(chargeDto);
        charge.setId(null);
        Charge newCharge = chargeRepository.save(charge);
        return chargeToDto(newCharge);
    }

    public Optional<ChargeDTO> getCharge(Long id) {
        return chargeRepository.findById(id).map(this::chargeToDto);
    }

    public Optional<ChargeDTO> updateCharge(ChargeDTO chargeDto) {
        Charge charge = dtoToCharge(chargeDto);
        if (chargeRepository.existsById(charge.getId())) {
            Charge newCharge = chargeRepository.save(charge);
            return Optional.of(chargeToDto(newCharge));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteCharge(Long id) {
        if (chargeRepository.existsById(id)) {
            chargeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private ChargeDTO chargeToDto(Charge charge) {
        return objectMapper.convertValue(charge, ChargeDTO.class);
    }

    private Charge dtoToCharge(ChargeDTO chargeDTO) {
        return objectMapper.convertValue(chargeDTO, Charge.class);
    }

}
