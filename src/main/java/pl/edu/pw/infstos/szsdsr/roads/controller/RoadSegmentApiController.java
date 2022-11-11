package pl.edu.pw.infstos.szsdsr.roads.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.RoadSegmentsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadSegmentDTO;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadSegmentService;

import java.util.List;
import java.util.Optional;

@RestController
public class RoadSegmentApiController implements RoadSegmentsApi {
    
    private final RoadSegmentService roadSegmentService;

    public RoadSegmentApiController(RoadSegmentService roadSegmentService) {
        this.roadSegmentService = roadSegmentService;
    }

    @Override
    public ResponseEntity<List<RoadSegmentDTO>> addRoadSegments(List<RoadSegmentDTO> roadSegmentDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roadSegmentService.addAllRoadSegments(roadSegmentDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteRoadSegment(Long roadSegmentId) {
        if (roadSegmentService.deleteRoadSegment(roadSegmentId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<RoadSegmentDTO>> getAllRoadSegments() {
        return ResponseEntity.ok(roadSegmentService.getAllRoadSegments());
    }

    @Override
    public ResponseEntity<RoadSegmentDTO> getRoadSegment(Long roadSegmentId) {
        Optional<RoadSegmentDTO> maybeRoadSegmentDTO = roadSegmentService.getRoadSegment(roadSegmentId);
        if (maybeRoadSegmentDTO.isPresent()) {
            return ResponseEntity.ok(maybeRoadSegmentDTO.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<RoadSegmentDTO> updateRoadSegment(Long roadSegmentId, RoadSegmentDTO roadSegmentDTO) {
        try {
            roadSegmentDTO.setId(roadSegmentId);
            Optional<RoadSegmentDTO> rsdto = roadSegmentService.updateRoadSegment(roadSegmentDTO);
            if (rsdto.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(rsdto.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
