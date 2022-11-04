package pl.edu.pw.infstos.szsdsr.penalties.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.PenaltyDTO;
import pl.edu.pw.infstos.szsdsr.penalties.Penalty;
import pl.edu.pw.infstos.szsdsr.penalties.repositories.PenaltyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;

    private final ModelMapper modelMapper;

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public PenaltyService(
            @Autowired PenaltyRepository penaltyRepository,
            @Autowired ModelMapper modelMapper
    ) {
        this.penaltyRepository = penaltyRepository;

        this.modelMapper = modelMapper;
    }

    public PenaltyDTO addPenalty(PenaltyDTO penaltyDto) {
        Penalty penalty = dtoToPenalty(penaltyDto);
        penalty.setId(null);
        Penalty newPenalty = penaltyRepository.save(penalty);
        return penaltyToDto(newPenalty);
    }

    public List<PenaltyDTO> getAllPenalties(UUID userId) {
        List<Penalty> penalties = penaltyRepository.findAllByUserId(userId);
        return penalties.stream().map(this::penaltyToDto).collect(Collectors.toList());
    }

    public Optional<PenaltyDTO> getPenalty(Long id) {
        return penaltyRepository.findById(id).map(this::penaltyToDto);
    }

    public Optional<PenaltyDTO> updatePenalty(PenaltyDTO penaltyDto) {
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

    private PenaltyDTO penaltyToDto(Penalty penalty) {
        return objectMapper.convertValue(penalty, PenaltyDTO.class);
    }

    private Penalty dtoToPenalty(PenaltyDTO penaltyDTO) {
        return objectMapper.convertValue(penaltyDTO, Penalty.class);
    }

}
