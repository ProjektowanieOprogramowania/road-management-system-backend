package pl.edu.pw.infstos.szsdsr.payments.penalty;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;

    private final ModelMapper modelMapper;

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

    public List<PenaltyDTO> getAllPenalties(Long userId) {
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
        return modelMapper.map(penalty, PenaltyDTO.class);
    }

    private Penalty dtoToPenalty(PenaltyDTO penaltyDTO) {
        return modelMapper.map(penaltyDTO, Penalty.class);
    }

}
