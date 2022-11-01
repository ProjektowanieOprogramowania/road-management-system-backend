package pl.edu.pw.infstos.szsdsr.payments.passing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassingService {

    private final PassingRepository passingRepository;

    public PassingService(@Autowired PassingRepository passingRepository) {
        this.passingRepository = passingRepository;
    }

    public Passing addPassing(Passing passing) {
        passing.setId(null);
        return passingRepository.save(passing);
    }

    public Optional<Passing> getPassing(Long id) {
        return passingRepository.findById(id);
    }

    public Optional<Passing> updatePassing(Passing passing) {
        if (passingRepository.existsById(passing.getId())) {
            return Optional.of(passingRepository.save(passing));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletePassing(Long id) {
        if (passingRepository.existsById(id)) {
            passingRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
