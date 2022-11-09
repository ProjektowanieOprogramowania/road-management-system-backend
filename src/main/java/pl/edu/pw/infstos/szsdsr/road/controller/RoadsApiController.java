package pl.edu.pw.infstos.szsdsr.road.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.RoadsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadDTO;
import pl.edu.pw.infstos.szsdsr.road.services.RoadService;

import java.util.List;

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
}
