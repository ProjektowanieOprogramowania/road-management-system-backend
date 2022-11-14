package pl.edu.pw.infstos.szsdsr.roads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.roads.domain.RoadNode;

public interface RoadNodeRepository extends JpaRepository<RoadNode, Long> {
}
