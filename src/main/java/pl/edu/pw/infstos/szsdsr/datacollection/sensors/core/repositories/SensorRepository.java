package pl.edu.pw.infstos.szsdsr.datacollection.sensors.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.datacollection.sensors.core.domain.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
