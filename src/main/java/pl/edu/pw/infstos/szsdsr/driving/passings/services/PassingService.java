package pl.edu.pw.infstos.szsdsr.driving.passings.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.driving.passings.Passing;
import pl.edu.pw.infstos.szsdsr.generated.models.PassingDTO;
import pl.edu.pw.infstos.szsdsr.driving.passings.repositories.PassingRepository;

import java.util.Optional;

@Service
public class PassingService {

    private final PassingRepository passingRepository;
    private final ObjectMapper objectMapper;

    public PassingService(@Autowired PassingRepository passingRepository,
                          @Autowired ObjectMapper objectMapper) {
        this.passingRepository = passingRepository;
        this.objectMapper = objectMapper;
    }

    public PassingDTO addPassing(PassingDTO passingDto) {
        Passing passing = dtoToPassing(passingDto);
        passing.setId(null);
        Passing newPassing = passingRepository.save(passing);
        return passingToDto(newPassing);
    }

    public Optional<PassingDTO> getPassing(Long id) {
        return passingRepository.findById(id).map(this::passingToDto);
    }

    public Optional<PassingDTO> updatePassing(PassingDTO passingDto) {
        Passing passing = dtoToPassing(passingDto);
        if (passingRepository.existsById(passing.getId())) {
            Passing newPassing = passingRepository.save(passing);
            return Optional.of(passingToDto(newPassing));
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

    private PassingDTO passingToDto(Passing passing) {
        return objectMapper.convertValue(passing, PassingDTO.class);
    }

    private Passing dtoToPassing(PassingDTO passingDto) {
        return objectMapper.convertValue(passingDto, Passing.class);
    }

}
