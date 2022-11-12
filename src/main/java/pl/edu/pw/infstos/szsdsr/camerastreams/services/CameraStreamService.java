package pl.edu.pw.infstos.szsdsr.camerastreams.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.camerastreams.domain.CameraStream;
import pl.edu.pw.infstos.szsdsr.camerastreams.repositories.CameraStreamRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.CameraStreamDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.VoivodeshipDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CameraStreamService {

    private final CameraStreamRepository cameraStreamRepository;
    private final ObjectMapper objectMapper;

    public CameraStreamService(@Autowired CameraStreamRepository cameraStreamRepository,
                               @Autowired ObjectMapper objectMapper) {
        this.cameraStreamRepository = cameraStreamRepository;
        this.objectMapper = objectMapper;
    }

    public List<CameraStreamDTO> getCameraStreamsByVoivodeship(VoivodeshipDTO voivodeship) {
        List<CameraStream> cameraStreams = cameraStreamRepository.findAllByVoivodeship(voivodeship);
        return cameraStreams.stream().map(this::cameraStreamToDto).collect(Collectors.toList());
    }

    public CameraStreamDTO addCameraStream(CameraStreamDTO cameraStreamDTO) {
        CameraStream cameraStream = dtoToCameraStream(cameraStreamDTO);
        cameraStream.setId(null);
        CameraStream createdCameraStream = cameraStreamRepository.save(cameraStream);
        return cameraStreamToDto(createdCameraStream);
    }

    private CameraStreamDTO cameraStreamToDto(CameraStream cameraStream) {
        return objectMapper.convertValue(cameraStream, CameraStreamDTO.class);
    }

    private CameraStream dtoToCameraStream(CameraStreamDTO cameraStreamDTO) {
        return objectMapper.convertValue(cameraStreamDTO, CameraStream.class);
    }

}
