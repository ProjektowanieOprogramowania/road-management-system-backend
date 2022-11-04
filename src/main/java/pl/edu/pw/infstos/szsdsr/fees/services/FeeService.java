package pl.edu.pw.infstos.szsdsr.fees.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.fees.repositories.FeeRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.FeeDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeeService {
    private final FeeRepository feeRepository;
    private final ObjectMapper objectMapper;

    public FeeService(@Autowired FeeRepository feeRepository, @Autowired ObjectMapper objectMapper) {
        this.feeRepository = feeRepository;
        this.objectMapper = objectMapper;
    }

    public List<FeeDTO> getUsersFee(UUID userId) {
        return feeRepository.getAllUsersFees(userId)
                .stream()
                .map(fee -> objectMapper.convertValue(fee, FeeDTO.class))
                .collect(Collectors.toList());
    }
}
