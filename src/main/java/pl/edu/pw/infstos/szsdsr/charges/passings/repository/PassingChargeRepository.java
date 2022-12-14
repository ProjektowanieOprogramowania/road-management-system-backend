package pl.edu.pw.infstos.szsdsr.charges.passings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.PassingCharge;

import java.util.List;
import java.util.UUID;

public interface PassingChargeRepository extends JpaRepository<PassingCharge, Long> {

    @Query(value = """
            SELECT pc
            FROM PassingCharge pc
            JOIN pc.charge c
            WHERE c.paid = false AND c.userId = :userUUID
            """)
    List<PassingCharge> findNotPaidCharges(UUID userUUID);

}
