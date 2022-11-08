package pl.edu.pw.infstos.szsdsr.charges.passings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.SubscriptionInfo;

public interface SubscriptionInfoRepository extends JpaRepository<SubscriptionInfo, Long> {
}