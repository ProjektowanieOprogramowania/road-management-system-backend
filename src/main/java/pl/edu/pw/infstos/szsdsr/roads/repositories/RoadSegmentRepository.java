package pl.edu.pw.infstos.szsdsr.roads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pw.infstos.szsdsr.roads.domain.RoadSegment;

import java.util.List;

public interface RoadSegmentRepository extends JpaRepository<RoadSegment, Long> {

    @Query(value = """
            SELECT rs.id
            FROM RoadSegment rs
            WHERE :nodeId IN (rs.startNode.id, rs.endNode.id)
            """)
    List<Integer> findRoadSegmentIdsByNodeId(Long nodeId);
}
