package pl.edu.pw.infstos.szsdsr.tolls.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.tolls.Subscription;
import pl.edu.pw.infstos.szsdsr.tolls.SubscriptionInfo;

public interface SubscriptionInfoRepository extends JpaRepository<SubscriptionInfo, Long> {
}