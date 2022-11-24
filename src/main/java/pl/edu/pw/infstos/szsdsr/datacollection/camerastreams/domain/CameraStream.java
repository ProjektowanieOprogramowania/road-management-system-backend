package pl.edu.pw.infstos.szsdsr.datacollection.camerastreams.domain;

import pl.edu.pw.infstos.szsdsr.generated.models.CameraStreamProtocolDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.VoivodeshipDTO;
import pl.edu.pw.infstos.szsdsr.localization.Localization;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CameraStream {

    @GeneratedValue
    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @OneToOne
    private Localization localization;

    @NotNull
    private VoivodeshipDTO voivodeship;

    @NotNull
    private String url;

    @NotNull
    private CameraStreamProtocolDTO protocol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public VoivodeshipDTO getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(VoivodeshipDTO voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CameraStreamProtocolDTO getProtocol() {
        return protocol;
    }

    public void setProtocol(CameraStreamProtocolDTO protocol) {
        this.protocol = protocol;
    }
}
