package pl.edu.pw.infstos.szsdsr.fees.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.fees.Fee;

import java.util.List;
import java.util.UUID;

public interface FeeRepository extends JpaRepository<Fee, Long> {
}
