package pl.edu.pw.infstos.szsdsr.passings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.passings.Passing;

public interface PassingRepository extends JpaRepository<Passing, Long> {
}
