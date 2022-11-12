package pl.edu.pw.infstos.szsdsr.sensors.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.SensorsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.LocalizationDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.SensorDTO;
import pl.edu.pw.infstos.szsdsr.localization.services.LocalizationService;
import pl.edu.pw.infstos.szsdsr.sensors.core.services.SensorService;

import java.util.List;

@RestController
public class SensorApiController implements SensorsApi {

    private final SensorService sensorService;
    private final LocalizationService localizationService;

    public SensorApiController(@Autowired SensorService sensorService,
                               @Autowired LocalizationService localizationService) {
        this.sensorService = sensorService;
        this.localizationService = localizationService;
    }

    @Override
    public ResponseEntity<SensorDTO> addSensor(SensorDTO sensorDTO) {
        try {
            LocalizationDTO createdLocalization = localizationService.addLocalization(sensorDTO.getLocalization());
            sensorDTO.setLocalization(createdLocalization);
            SensorDTO createdSensor = sensorService.addSensor(sensorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensors());
    }
}
