package pl.edu.pw.infstos.szsdsr.tariffs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.tariffs.Tariff;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
