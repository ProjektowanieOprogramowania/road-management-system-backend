package pl.edu.pw.infstos.szsdsr.road.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pw.infstos.szsdsr.road.domain.Road;

import java.util.List;

public interface RoadRepository extends JpaRepository<Road, Long> {

    @Query("select r.name from Road r where r.id in :ids")
    List<String> findNamesByIds(List<Long> ids);

    @Query("select r from Road r where r.id in :ids")
    List<Road> findByIds(List<Long> ids);
}
