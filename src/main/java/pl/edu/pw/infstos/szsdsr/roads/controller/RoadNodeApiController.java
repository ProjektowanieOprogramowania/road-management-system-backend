package pl.edu.pw.infstos.szsdsr.roads.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.RoadNodesApi;
import pl.edu.pw.infstos.szsdsr.generated.models.LocalizationDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadNodeDTO;
import pl.edu.pw.infstos.szsdsr.localization.services.LocalizationService;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadNodeService;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadSegmentService;

import java.util.List;
import java.util.Optional;

@RestController
public class RoadNodeApiController implements RoadNodesApi {

    private final RoadNodeService roadNodeService;
    private final RoadSegmentService roadSegmentService;
    private final LocalizationService localizationService;

    public RoadNodeApiController(RoadNodeService roadNodeService,
                                 RoadSegmentService roadSegmentService,
                                 LocalizationService localizationService) {
        this.roadNodeService = roadNodeService;
        this.roadSegmentService = roadSegmentService;
        this.localizationService = localizationService;
    }

    @Override
    public ResponseEntity<List<RoadNodeDTO>> addRoadNodes(List<RoadNodeDTO> roadNodeDTO) {
        roadNodeDTO.forEach(rn -> {
            LocalizationDTO l = localizationService.addLocalization(rn.getLocalization());
            rn.setLocalization(l);
        });

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roadNodeService.addAllRoadNodes(roadNodeDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteRoadNode(Long roadNodeId) {
        List<Integer> roadSegmentsContainingNode = roadSegmentService.findRoadSegmentIdsByNodeId(roadNodeId);

        if (!roadSegmentsContainingNode.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

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
