package pl.edu.pw.infstos.szsdsr.fees.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pw.infstos.szsdsr.fees.Fee;

import java.util.List;
import java.util.UUID;

public interface PassingChargeRepository2 extends JpaRepository<Fee, Long> {
    @Query(value = "SELECT f FROM Fee f WHERE Fee.toll.id = IN (SELECT id FROM Toll WHERE userId = ?1)", nativeQuery = true)
    List<Fee> getAllUsersFees(UUID userId);

}
