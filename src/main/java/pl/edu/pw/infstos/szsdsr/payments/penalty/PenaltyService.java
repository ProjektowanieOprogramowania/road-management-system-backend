package pl.edu.pw.infstos.szsdsr.payments.penalty;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.payments.tariff.Tariff;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffDTO;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;

    public PenaltyService(@Autowired PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    public List<Penalty> getAllPenalties(Long userId) {
        return penaltyRepository.findAllByUserId(userId);
    }

}
