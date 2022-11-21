package pl.edu.pw.infstos.szsdsr.roads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.RoadsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadDTO;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadService;

import java.util.List;
import java.util.Optional;

@RestController
public class RoadsApiController implements RoadsApi {

    private final RoadService roadService;

    public RoadsApiController(@Autowired RoadService roadService) {
        this.roadService = roadService;
    }

    @Override
    public ResponseEntity<List<RoadDTO>> getAllRoads() {
        return ResponseEntity.ok(roadService.getAllRoads());
    }

    @Override
    public ResponseEntity<RoadDTO> addRoad(RoadDTO roadDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roadService.addRoad(roadDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteRoad(Long roadId) {
        try {
            if (roadService.deleteRoad(roadId)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<RoadDTO> getRoad(Long roadId) {
        Optional<RoadDTO> roadDTO = roadService.getRoad(roadId);
        if (roadDTO.isPresent()) {
            return ResponseEntity.ok(roadDTO.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<RoadDTO> updateRoad(Long roadId, RoadDTO roadDTO) {
        /*
        try {
            roadDTO.setId(roadId);
            Optional<RoadDTO> rdto = roadService.updateRoad(roadDTO);
            if (rdto.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(rdto.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
         */
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
