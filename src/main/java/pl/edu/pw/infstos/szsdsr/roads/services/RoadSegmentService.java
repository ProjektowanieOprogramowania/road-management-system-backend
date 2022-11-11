package pl.edu.pw.infstos.szsdsr.roads.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadSegmentDTO;
import pl.edu.pw.infstos.szsdsr.roads.domain.RoadSegment;
import pl.edu.pw.infstos.szsdsr.roads.repositories.RoadSegmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoadSegmentService {
    private final RoadSegmentRepository roadSegmentRepository;
    private final ObjectMapper objectMapper;

    public RoadSegmentService(@Autowired RoadSegmentRepository roadSegmentRepository,
                              @Autowired ObjectMapper objectMapper) {
        this.roadSegmentRepository = roadSegmentRepository;
        this.objectMapper = objectMapper;
    }

    public RoadSegmentDTO addRoadSegment(RoadSegmentDTO roadSegmentDto) {
        RoadSegment roadSegment = dtoToRoadSegment(roadSegmentDto);
        roadSegment.setId(null);
        RoadSegment newRoadSegment = roadSegmentRepository.save(roadSegment);
        return roadSegmentToDto(newRoadSegment);
    }

    public List<RoadSegmentDTO> findAllRoadSegments() {
        return roadSegmentRepository.findAll()
                .stream()
                .map(this::roadSegmentToDto)
                .collect(Collectors.toList());
    }

    public Optional<RoadSegmentDTO> getRoadSegment(Long id) {
        return roadSegmentRepository.findById(id).map(this::roadSegmentToDto);
    }

    public Optional<RoadSegmentDTO> updateRoadSegment(RoadSegmentDTO roadSegmentDto) {
        RoadSegment roadSegment = dtoToRoadSegment(roadSegmentDto);
        if (roadSegmentRepository.existsById(roadSegment.getId())) {
            RoadSegment newRoadSegment = roadSegmentRepository.save(roadSegment);
            return Optional.of(roadSegmentToDto(newRoadSegment));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteRoadSegment(Long id) {
        if (roadSegmentRepository.existsById(id)) {
            roadSegmentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private RoadSegmentDTO roadSegmentToDto(RoadSegment roadSegment) {
        return objectMapper.convertValue(roadSegment, RoadSegmentDTO.class);
    }

    private RoadSegment dtoToRoadSegment(RoadSegmentDTO roadSegmentDto) {
        return objectMapper.convertValue(roadSegmentDto, RoadSegment.class);
    }
}
