package pl.edu.pw.infstos.szsdsr.tolls.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.tolls.Toll;

import java.util.List;
import java.util.UUID;

public interface TollRepository extends JpaRepository<Toll, Long> {
    List<Toll> findByIsPaidFalseAndUserId(UUID userId);
}
