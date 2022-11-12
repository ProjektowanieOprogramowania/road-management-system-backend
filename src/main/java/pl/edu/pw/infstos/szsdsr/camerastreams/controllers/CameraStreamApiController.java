package pl.edu.pw.infstos.szsdsr.camerastreams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.camerastreams.services.CameraStreamService;
import pl.edu.pw.infstos.szsdsr.generated.api.CameraStreamApi;
import pl.edu.pw.infstos.szsdsr.generated.models.CameraStreamDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.VoivodeshipDTO;

import java.util.List;

@RestController
public class CameraStreamApiController implements CameraStreamApi {

    private final CameraStreamService cameraStreamService;

    public CameraStreamApiController(@Autowired CameraStreamService cameraStreamService) {
        this.cameraStreamService = cameraStreamService;
    }

    @Override
    public ResponseEntity<List<CameraStreamDTO>> getCameraStreamsByVoivodeship(VoivodeshipDTO voivodeship) {
        return ResponseEntity.ok(cameraStreamService.getCameraStreamsByVoivodeship(voivodeship));
    }

    @Override
    public ResponseEntity<CameraStreamDTO> addCameraStream(CameraStreamDTO cameraStreamDTO) {
        CameraStreamDTO createdCamera = cameraStreamService.addCameraStream(cameraStreamDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCamera);
    }
}
