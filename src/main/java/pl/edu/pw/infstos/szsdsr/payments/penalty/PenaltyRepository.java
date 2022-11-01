package pl.edu.pw.infstos.szsdsr.payments.penalty;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    List<Penalty> findAllByUserId(Long userId);
}
