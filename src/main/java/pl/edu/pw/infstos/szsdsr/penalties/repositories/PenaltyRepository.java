package pl.edu.pw.infstos.szsdsr.penalties.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.penalties.Penalty;

import java.util.List;
import java.util.UUID;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    List<Penalty> findAllByUserId(UUID userId);
}
