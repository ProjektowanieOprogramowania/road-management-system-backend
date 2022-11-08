package pl.edu.pw.infstos.szsdsr.charges.passings.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.PassingCharge;
import pl.edu.pw.infstos.szsdsr.charges.passings.repository.PassingChargeRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.PassingChargeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PassingChargeService {
    private final PassingChargeRepository passingChargeRepository;
    private final ObjectMapper objectMapper;

    public PassingChargeService(PassingChargeRepository passingChargeRepository, ObjectMapper objectMapper) {
        this.passingChargeRepository = passingChargeRepository;
        this.objectMapper = objectMapper;
    }

    public List<PassingChargeDTO> getNotPaidPassingCharges(UUID uuid) {
        return passingChargeRepository.findNotPaidCharges(uuid)
                .stream()
                .map(this::passingChargeToDto)
                .toList();
    }

    public PassingChargeDTO addPassingCharge(PassingChargeDTO passingChargeDto) {
        PassingCharge passingCharge = dtoToPassingCharge(passingChargeDto);
        passingCharge.setId(null);
        PassingCharge newPassingCharge = passingChargeRepository.save(passingCharge);
        return passingChargeToDto(newPassingCharge);
    }

    public Optional<PassingChargeDTO> getPassingCharge(Long id) {
        return passingChargeRepository.findById(id).map(this::passingChargeToDto);
    }

    public Optional<PassingChargeDTO> updatePassingCharge(PassingChargeDTO passingChargeDto) {
        PassingCharge passingCharge = dtoToPassingCharge(passingChargeDto);
        if (passingChargeRepository.existsById(passingCharge.getId())) {
            PassingCharge newPassingCharge = passingChargeRepository.save(passingCharge);
            return Optional.of(passingChargeToDto(newPassingCharge));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletePassingCharge(Long id) {
        if (passingChargeRepository.existsById(id)) {
            passingChargeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private PassingChargeDTO passingChargeToDto(PassingCharge passingCharge) {
        return objectMapper.convertValue(passingCharge, PassingChargeDTO.class);
    }

    private PassingCharge dtoToPassingCharge(PassingChargeDTO passingChargeDTO) {
        return objectMapper.convertValue(passingChargeDTO, PassingCharge.class);
    }

}
