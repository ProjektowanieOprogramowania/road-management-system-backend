package pl.edu.pw.infstos.szsdsr.payments.penalty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;

    public PenaltyService(@Autowired PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    public Penalty addPenalty(Penalty penalty) {
        penalty.setId(null);
        return penaltyRepository.save(penalty);
    }

    public List<Penalty> getAllPenalties(Long userId) {
        return penaltyRepository.findAllByUserId(userId);
    }

    public Optional<Penalty> getPenalty(Long id) {
        return penaltyRepository.findById(id);
    }

    public Optional<Penalty> updatePenalty(Penalty penalty) {
        if (penaltyRepository.existsById(penalty.getId())) {
            return Optional.of(penaltyRepository.save(penalty));
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

}
