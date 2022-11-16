package pl.edu.pw.infstos.szsdsr.datacollection.sensors.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.datacollection.sensors.core.repositories.SensorRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.SensorDTO;
import pl.edu.pw.infstos.szsdsr.datacollection.sensors.core.domain.Sensor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ObjectMapper objectMapper;

    public SensorService(@Autowired SensorRepository sensorRepository,
                         @Autowired ObjectMapper objectMapper) {
        this.sensorRepository = sensorRepository;
        this.objectMapper = objectMapper;
    }

    public List<SensorDTO> getAllSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensors.stream().map(this::sensorToDto).collect(Collectors.toList());
    }

    public SensorDTO addSensor(SensorDTO sensorDto) {
        Sensor sensor = dtoToSensor(sensorDto);
        sensor.setId(null);
        sensor.setWebhookUrl(createWebhookUrl());
        Sensor createdSensor = sensorRepository.save(sensor);
        return sensorToDto(createdSensor);
    }

    private String createWebhookUrl() {
        // TODO?
        return "";
    }

    private Sensor dtoToSensor(SensorDTO sensorDTO) {
        return objectMapper.convertValue(sensorDTO, Sensor.class);
    }

    private SensorDTO sensorToDto(Sensor sensor) {
        return objectMapper.convertValue(sensor, SensorDTO.class);
    }

}
