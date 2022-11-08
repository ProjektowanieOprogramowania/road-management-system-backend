package pl.edu.pw.infstos.szsdsr.charges.passings.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.SubscriptionInfo;
import pl.edu.pw.infstos.szsdsr.charges.passings.repository.SubscriptionInfoRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionInfoDTO;

@Service
public class SubscriptionInfoService {

    private final SubscriptionInfoRepository subscriptionInfoRepo;
    private final ObjectMapper objectMapper;

    public SubscriptionInfoService(SubscriptionInfoRepository subscriptionInfoRepo, ObjectMapper objectMapper) {
        this.subscriptionInfoRepo = subscriptionInfoRepo;
        this.objectMapper = objectMapper;
    }

    public SubscriptionInfoDTO getSubscriptionInfo() {
        var subscription = subscriptionInfoRepo
                .findAll().get(0);

        return mapToDTO(subscription);
    }

    private SubscriptionInfoDTO mapToDTO(SubscriptionInfo subscriptionInfo) {
        return objectMapper.convertValue(subscriptionInfo, SubscriptionInfoDTO.class);
    }

}
