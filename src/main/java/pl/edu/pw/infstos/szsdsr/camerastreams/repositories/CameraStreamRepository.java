package pl.edu.pw.infstos.szsdsr.camerastreams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.infstos.szsdsr.camerastreams.domain.CameraStream;
import pl.edu.pw.infstos.szsdsr.generated.models.VoivodeshipDTO;

import java.util.List;

public interface CameraStreamRepository extends JpaRepository<CameraStream, Long> {
    List<CameraStream> findAllByVoivodeship(VoivodeshipDTO voivodeship);
}
