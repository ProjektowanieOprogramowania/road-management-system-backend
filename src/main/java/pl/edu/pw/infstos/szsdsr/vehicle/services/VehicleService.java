package pl.edu.pw.infstos.szsdsr.vehicle.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.VehicleDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.VehicleTypeDTO;
import pl.edu.pw.infstos.szsdsr.vehicle.Vehicle;
import pl.edu.pw.infstos.szsdsr.vehicle.repositories.VehicleRepository;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public VehicleService(@Autowired VehicleRepository vehicleRepository,
                          @Autowired ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
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
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    private Vehicle dtoToVehicle(VehicleDTO vehicleDto) {
        //Vehicle vehicle = modelMapper.map(vehicleDto, Vehicle.class);
        //vehicle.setVehicleType(vehicleTypeStringToEnum(vehicleDto.getVehicleType()));
        return objectMapper.convertValue(vehicleDto, Vehicle.class);
    }
}
