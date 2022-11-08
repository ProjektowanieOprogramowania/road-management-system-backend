package pl.edu.pw.infstos.szsdsr.charges.passings.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.Subscription;
import pl.edu.pw.infstos.szsdsr.charges.passings.repository.SubscriptionRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionInfoDTO;

import java.util.UUID;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepo;
    private final ObjectMapper objectMapper;

    public SubscriptionService(SubscriptionRepository subscriptionRepo, ObjectMapper objectMapper) {
        this.subscriptionRepo = subscriptionRepo;
        this.objectMapper = objectMapper;
    }

    public SubscriptionDTO getUserSubscription(UUID uuid) {
        var subscription = subscriptionRepo.findByUser_Uuid(uuid);

        return mapToDTO(subscription);
    }

    private SubscriptionDTO mapToDTO(Subscription subscription) {
        return objectMapper.convertValue(subscription, SubscriptionDTO.class);
    }

}
