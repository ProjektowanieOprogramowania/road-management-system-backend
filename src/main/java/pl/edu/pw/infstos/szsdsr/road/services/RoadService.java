package pl.edu.pw.infstos.szsdsr.road.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadDTO;
import pl.edu.pw.infstos.szsdsr.road.domain.Road;
import pl.edu.pw.infstos.szsdsr.road.repositories.RoadRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoadService {
    private final RoadRepository roadRepository;
    private final ObjectMapper objectMapper;

    public RoadService(@Autowired RoadRepository roadRepository,
                       @Autowired ObjectMapper objectMapper) {
        this.roadRepository = roadRepository;
        this.objectMapper = objectMapper;
    }

    public RoadDTO addRoad(RoadDTO roadDto) {
        Road road = dtoToRoad(roadDto);
        road.setId(null);
        Road newRoad = roadRepository.save(road);
        return roadToDto(newRoad);
    }

    public Optional<RoadDTO> getRoad(Long id) {
        return roadRepository.findById(id).map(this::roadToDto);
    }

    public Optional<RoadDTO> updateRoad(RoadDTO roadDto) {
        Road road = dtoToRoad(roadDto);
        if (roadRepository.existsById(road.getId())) {
            Road newRoad = roadRepository.save(road);
            return Optional.of(roadToDto(newRoad));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteRoad(Long id) {
        if (roadRepository.existsById(id)) {
            roadRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<String> findNamesByIds(List<Long> ids) {
        return roadRepository.findNamesByIds(ids);
    }

    public List<Road> findByIds(List<Long> ids) {
        return roadRepository.findByIds(ids);
    }

    private RoadDTO roadToDto(Road road) {
        return objectMapper.convertValue(road, RoadDTO.class);
    }

    private Road dtoToRoad(RoadDTO roadDto) {
        return objectMapper.convertValue(roadDto, Road.class);
    }

    public List<RoadDTO> getAllRoads() {
        return roadRepository.findAll()
                .stream()
                .map(this::roadToDto)
                .collect(Collectors.toList());
    }
}
