package pl.edu.pw.infstos.szsdsr.localization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.localization.Localization;

public interface LocalizationRepository extends JpaRepository<Localization, Long> {
}
