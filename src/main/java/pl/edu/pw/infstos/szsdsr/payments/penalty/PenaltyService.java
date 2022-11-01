package pl.edu.pw.infstos.szsdsr.payments.penalty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
