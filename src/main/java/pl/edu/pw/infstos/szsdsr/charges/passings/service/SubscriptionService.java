package pl.edu.pw.infstos.szsdsr.charges.passings.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.Subscription;
import pl.edu.pw.infstos.szsdsr.charges.passings.repository.SubscriptionRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ObjectMapper objectMapper;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, ObjectMapper objectMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.objectMapper = objectMapper;
    }

    public List<SubscriptionDTO> getUserSubscriptions(UUID uuid) {
        return subscriptionRepository.findAllBySubscriberId(uuid)
                .stream()
                .map(this::subscriptionToDto)
                .collect(Collectors.toList());
    }

    public SubscriptionDTO addSubscription(SubscriptionDTO subscriptionDto) {
        Subscription subscription = dtoToSubscription(subscriptionDto);
        subscription.setId(null);
        Subscription newSubscription = subscriptionRepository.save(subscription);
        return subscriptionToDto(newSubscription);
    }

    public Optional<SubscriptionDTO> getSubscription(Long id) {
        return subscriptionRepository.findById(id).map(this::subscriptionToDto);
    }

    public Optional<SubscriptionDTO> updateSubscription(SubscriptionDTO subscriptionDto) {
        Subscription subscription = dtoToSubscription(subscriptionDto);
        if (subscriptionRepository.existsById(subscription.getId())) {
            Subscription newSubscription = subscriptionRepository.save(subscription);
            return Optional.of(subscriptionToDto(newSubscription));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteSubscription(Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private SubscriptionDTO subscriptionToDto(Subscription subscription) {
        return objectMapper.convertValue(subscription, SubscriptionDTO.class);
    }

    private Subscription dtoToSubscription(SubscriptionDTO subscriptionDto) {
        return objectMapper.convertValue(subscriptionDto, Subscription.class);
    }

}
