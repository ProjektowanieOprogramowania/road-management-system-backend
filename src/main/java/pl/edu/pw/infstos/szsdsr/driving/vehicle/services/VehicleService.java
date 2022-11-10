package pl.edu.pw.infstos.szsdsr.driving.vehicle.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.driving.vehicle.Vehicle;
import pl.edu.pw.infstos.szsdsr.generated.models.VehicleDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.VehicleTypeDTO;
import pl.edu.pw.infstos.szsdsr.driving.vehicle.repositories.VehicleRepository;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ObjectMapper objectMapper;

    public VehicleService(@Autowired VehicleRepository vehicleRepository,
                          @Autowired ObjectMapper objectMapper) {
        this.vehicleRepository = vehicleRepository;
        this.objectMapper = objectMapper;
    }

    public VehicleDTO addVehicle(VehicleDTO vehicleDto) {
        Vehicle vehicle = dtoToVehicle(vehicleDto);
        vehicle.setId(null);
        Vehicle newVehicle = vehicleRepository.save(vehicle);
        return vehicleToDto(newVehicle);
    }

    public Optional<VehicleDTO> getVehicle(Long id) {
        return vehicleRepository.findById(id).map(this::vehicleToDto);
    }

    public Optional<VehicleDTO> updateVehicle(VehicleDTO vehicleDto) {
        Vehicle vehicle = dtoToVehicle(vehicleDto);
        if (vehicleRepository.existsById(vehicle.getId())) {
            Vehicle newVehicle = vehicleRepository.save(vehicle);
            return Optional.of(vehicleToDto(newVehicle));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private VehicleTypeDTO vehicleTypeStringToEnum(String vt) {
        switch (vt) {
            case "car":
                return VehicleTypeDTO.CAR;
            case "truck":
                return VehicleTypeDTO.TRUCK;
            case "motorcycle":
                return VehicleTypeDTO.MOTORCYCLE;
            default:
                return VehicleTypeDTO.OTHER;
        }
    }

    private VehicleDTO vehicleToDto(Vehicle vehicle) {
        return objectMapper.convertValue(vehicle, VehicleDTO.class);
    }

    private Vehicle dtoToVehicle(VehicleDTO vehicleDto) {
        return objectMapper.convertValue(vehicleDto, Vehicle.class);
    }
}
