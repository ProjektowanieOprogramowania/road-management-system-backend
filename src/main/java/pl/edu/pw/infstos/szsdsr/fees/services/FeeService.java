package pl.edu.pw.infstos.szsdsr.fees.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.fees.repositories.FeeRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.FeeDTO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeeService {
    private final FeeRepository feeRepository;
    private final ModelMapper modelMapper;

    public FeeService(@Autowired FeeRepository feeRepository, @Autowired ModelMapper modelMapper) {
        this.feeRepository = feeRepository;
        this.modelMapper = modelMapper;
    }

    public List<FeeDTO> getUsersFee(UUID userId) {
        return feeRepository.getAllUsersFees(userId)
                .stream()
                .map(fee -> modelMapper.map(fee, FeeDTO.class))
                .collect(Collectors.toList());
    }
}
