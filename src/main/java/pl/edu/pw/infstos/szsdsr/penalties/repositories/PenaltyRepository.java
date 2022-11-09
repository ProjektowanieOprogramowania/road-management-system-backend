package pl.edu.pw.infstos.szsdsr.penalties.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pw.infstos.szsdsr.penalties.Penalty;

import java.util.List;
import java.util.UUID;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    @Query(value = """
            SELECT p
            FROM Penalty p
            WHERE p.charge.userId = :userId
            """)
    List<Penalty> findAllByUserId(UUID userId);
}
