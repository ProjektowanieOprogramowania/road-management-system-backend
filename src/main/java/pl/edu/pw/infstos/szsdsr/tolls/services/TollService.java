package pl.edu.pw.infstos.szsdsr.tolls.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.fees.repositories.FeeRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.NotPayedTollDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SubscriptionInfoDTO;
import pl.edu.pw.infstos.szsdsr.tolls.Subscription;
import pl.edu.pw.infstos.szsdsr.tolls.Toll;
import pl.edu.pw.infstos.szsdsr.tolls.TollDTO;
import pl.edu.pw.infstos.szsdsr.tolls.repositories.SubscriptionInfoRepository;
import pl.edu.pw.infstos.szsdsr.tolls.repositories.SubscriptionRepository;
import pl.edu.pw.infstos.szsdsr.tolls.repositories.TollRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class TollService {
    private final TollRepository tollRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final FeeRepository feeRepository;

    private final SubscriptionInfoRepository subscriptionInfoRepository;
    private final ModelMapper modelMapper;

    public TollService(
            @Autowired TollRepository tollRepository,
            @Autowired FeeRepository feeRepository,
            @Autowired SubscriptionRepository subscriptionRepository,
            @Autowired SubscriptionInfoRepository subscriptionInfoRepository,
            @Autowired ModelMapper modelMapper
    ) {
        this.tollRepository = tollRepository;
        this.feeRepository = feeRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionInfoRepository = subscriptionInfoRepository;
        this.modelMapper = modelMapper;
    }

    public List<TollDTO> getNotPaidTolls(UUID userId) {
        List<Toll> notPaidTollList = tollRepository.findByIsPaidFalseAndUserId(userId);
        return notPaidTollList.stream()
                .map(toll -> modelMapper.map(toll, TollDTO.class))
                .collect(Collectors.toList());
    }

    public NotPayedTollDTO payToll(Long tollId) {
        Optional<Toll> tollOptional = tollRepository.findById(tollId);
        if(tollOptional.isPresent()) {
            Toll toll = tollOptional.get();
            toll.setPaid(true);
            tollRepository.save(toll);
            return modelMapper.map(toll, NotPayedTollDTO.class);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String purchaseSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);
        subscriptionRepository.save(subscription);
        return "url?";
    }

    public List<SubscriptionInfoDTO> getSubscriptionInfo() {
        return subscriptionInfoRepository
                .findAll()
                .stream()
                .map(subscriptionInfo -> modelMapper.map(subscriptionInfo, SubscriptionInfoDTO.class))
                .collect(Collectors.toList());
    }
}
