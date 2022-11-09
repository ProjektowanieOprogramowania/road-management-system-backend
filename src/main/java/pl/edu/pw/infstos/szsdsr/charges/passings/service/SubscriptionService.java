package pl.edu.pw.infstos.szsdsr.charges.passings.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.Subscription;
import pl.edu.pw.infstos.szsdsr.charges.passings.repository.SubscriptionRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepo;
    private final ObjectMapper objectMapper;

    public SubscriptionService(SubscriptionRepository subscriptionRepo, ObjectMapper objectMapper) {
        this.subscriptionRepo = subscriptionRepo;
        this.objectMapper = objectMapper;
    }

    public List<SubscriptionDTO> getUserSubscriptions(UUID uuid) {
        var subscription = subscriptionRepo.findAllByUser_Uuid(uuid);

        return subscription.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private SubscriptionDTO mapToDTO(Subscription subscription) {
        return objectMapper.convertValue(subscription, SubscriptionDTO.class);
    }

}
