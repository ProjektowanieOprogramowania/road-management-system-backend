package pl.edu.pw.infstos.szsdsr.payments.passing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
