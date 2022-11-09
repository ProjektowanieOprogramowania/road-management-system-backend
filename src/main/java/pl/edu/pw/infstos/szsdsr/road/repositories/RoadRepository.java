package pl.edu.pw.infstos.szsdsr.road.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.road.domain.Road;

public interface RoadRepository extends JpaRepository<Road, Long> {
}
