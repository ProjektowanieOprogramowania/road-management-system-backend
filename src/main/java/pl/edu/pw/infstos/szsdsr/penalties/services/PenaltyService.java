package pl.edu.pw.infstos.szsdsr.penalties.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.PenaltyChargeDTO;
import pl.edu.pw.infstos.szsdsr.penalties.Penalty;
import pl.edu.pw.infstos.szsdsr.penalties.repositories.PenaltyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;

    private final ObjectMapper objectMapper;

    public PenaltyService(@Autowired PenaltyRepository penaltyRepository,
                          @Autowired ObjectMapper objectMapper) {
        this.penaltyRepository = penaltyRepository;
        this.objectMapper = objectMapper;
    }

    public PenaltyChargeDTO addPenalty(PenaltyChargeDTO penaltyDto) {
        Penalty penalty = dtoToPenalty(penaltyDto);
        penalty.setId(null);
        Penalty newPenalty = penaltyRepository.save(penalty);
        return penaltyToDto(newPenalty);
    }

    public List<PenaltyChargeDTO> getAllPenalties(UUID userId) {
        List<Penalty> penalties = penaltyRepository.findAllByUserId(userId);
        return penalties.stream().map(this::penaltyToDto).collect(Collectors.toList());
    }

    public Optional<PenaltyChargeDTO> getPenalty(Long id) {
        return penaltyRepository.findById(id).map(this::penaltyToDto);
    }

    public Optional<PenaltyChargeDTO> updatePenalty(PenaltyChargeDTO penaltyDto) {
        Penalty penalty = dtoToPenalty(penaltyDto);
        if (penaltyRepository.existsById(penalty.getId())) {
            Penalty newPenalty = penaltyRepository.save(penalty);
            return Optional.of(penaltyToDto(newPenalty));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletePenalty(Long id) {
        if (penaltyRepository.existsById(id)) {
            penaltyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private PenaltyChargeDTO penaltyToDto(Penalty penalty) {
        return objectMapper.convertValue(penalty, PenaltyChargeDTO.class);
    }

    private Penalty dtoToPenalty(PenaltyChargeDTO penaltyDTO) {
        return objectMapper.convertValue(penaltyDTO, Penalty.class);
    }

}
