package pl.edu.pw.infstos.szsdsr.charges.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.charges.core.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
