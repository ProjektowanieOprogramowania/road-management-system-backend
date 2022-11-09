package pl.edu.pw.infstos.szsdsr.charges.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;

import java.util.List;
import java.util.UUID;

public interface ChargeRepository extends JpaRepository<Charge, Long> {

    List<Charge> findAllByUserId(UUID userId);

}
