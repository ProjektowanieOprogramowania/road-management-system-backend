package pl.edu.pw.infstos.szsdsr.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
