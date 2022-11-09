package pl.edu.pw.infstos.szsdsr.charges.passings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.Subscription;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllByUser_Uuid(UUID uuid);

}
