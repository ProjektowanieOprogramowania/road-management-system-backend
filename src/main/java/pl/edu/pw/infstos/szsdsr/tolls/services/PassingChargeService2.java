package pl.edu.pw.infstos.szsdsr.tolls.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.pw.infstos.szsdsr.fees.repositories.PassingChargeRepository2;
import pl.edu.pw.infstos.szsdsr.generated.models.PassingChargeDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionInfoDTO;
import pl.edu.pw.infstos.szsdsr.tolls.Subscription;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.PassingCharge;
import pl.edu.pw.infstos.szsdsr.tolls.repositories.SubscriptionInfoRepository;
import pl.edu.pw.infstos.szsdsr.tolls.repositories.SubscriptionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
//@Service
public class PassingChargeService2 {
    private final SubscriptionRepository subscriptionRepository;
    private final PassingChargeRepository2 passingChargeRepo;

    private final SubscriptionInfoRepository subscriptionInfoRepository;
    private final ObjectMapper objectMapper;

    public PassingChargeService2(
            PassingChargeRepository2 passingChargeRepo,
            SubscriptionRepository subscriptionRepository,
            SubscriptionInfoRepository subscriptionInfoRepository,
            ObjectMapper objectMapper
    ) {
        this.passingChargeRepo = passingChargeRepo;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionInfoRepository = subscriptionInfoRepository;
        this.objectMapper = objectMapper;
    }

//    public List<PassingChargeDTO> getNotPaid(UUID userId) {
//        List<PassingCharge> notPaidPassingChargeList = tollRepository.findByIsPaidFalseAndUserId(userId);
//        return notPaidPassingChargeList.stream()
//                .map(passingCharge -> objectMapper.convertValue(passingCharge, PassingChargeDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    public PassingChargeDTO payToll(Long tollId) {
//        Optional<PassingCharge> tollOptional = tollRepository.findById(tollId);
//        if(tollOptional.isPresent()) {
//            PassingCharge passingCharge = tollOptional.get();
//            tollRepository.save(passingCharge);
//            return objectMapper.convertValue(passingCharge, PassingChargeDTO.class);
//        } else {
//            throw new IllegalArgumentException();
//        }
//    }

    public String purchaseSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = objectMapper.convertValue(subscriptionDTO, Subscription.class);
        subscriptionRepository.save(subscription);
        return "url?";
    }

    public List<SubscriptionInfoDTO> getSubscriptionInfo() {
        return subscriptionInfoRepository
                .findAll()
                .stream()
                .map(subscriptionInfo -> objectMapper.convertValue(subscriptionInfo, SubscriptionInfoDTO.class))
                .collect(Collectors.toList());
    }
}
