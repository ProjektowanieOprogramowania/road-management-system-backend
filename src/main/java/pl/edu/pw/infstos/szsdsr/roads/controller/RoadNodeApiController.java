package pl.edu.pw.infstos.szsdsr.roads.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.pw.infstos.szsdsr.generated.api.RoadNodesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadNodeDTO;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadNodeService;

import java.util.List;
import java.util.Optional;

public class RoadNodeApiController implements RoadNodesApi {

    private final RoadNodeService roadNodeService;

    public RoadNodeApiController(RoadNodeService roadNodeService) {
        this.roadNodeService = roadNodeService;
    }

    @Override
    public ResponseEntity<List<RoadNodeDTO>> addRoadNodes(List<RoadNodeDTO> roadNodeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roadNodeService.addAllRoadNodes(roadNodeDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteRoadNode(Long roadNodeId) {
        if (roadNodeService.deleteRoadNode(roadNodeId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<RoadNodeDTO>> getAllRoadNodes() {
        return ResponseEntity.ok(roadNodeService.getAllRoadNodes());
    }

    @Override
    public ResponseEntity<RoadNodeDTO> getRoadNode(Long roadNodeId) {
        Optional<RoadNodeDTO> maybeRoadNodeDTO = roadNodeService.getRoadNode(roadNodeId);
        if (maybeRoadNodeDTO.isPresent()) {
            return ResponseEntity.ok(maybeRoadNodeDTO.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<RoadNodeDTO> updateRoadNode(Long roadNodeId, RoadNodeDTO roadNodeDTO) {
        try {
            roadNodeDTO.setId(roadNodeId);
            Optional<RoadNodeDTO> rndto = roadNodeService.updateRoadNode(roadNodeDTO);
            if (rndto.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(rndto.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
