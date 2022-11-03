package pl.edu.pw.infstos.szsdsr.vehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.vehicle.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
