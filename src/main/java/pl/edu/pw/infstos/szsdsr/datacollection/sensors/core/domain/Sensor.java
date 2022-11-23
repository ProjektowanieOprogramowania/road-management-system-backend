package pl.edu.pw.infstos.szsdsr.datacollection.sensors.core.domain;

import pl.edu.pw.infstos.szsdsr.generated.models.SensorTypeDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.VoivodeshipDTO;
import pl.edu.pw.infstos.szsdsr.localization.Localization;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Sensor {

    @GeneratedValue
    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Localization localization;

    @NotNull
    private VoivodeshipDTO voivodeship;

    @NotNull
    private String serialNumber;

    @NotNull
    private SensorTypeDTO sensorType;

    @NotNull
    private Boolean enabled;

    private String webhookUrl;

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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public SensorTypeDTO getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorTypeDTO sensorType) {
        this.sensorType = sensorType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
}
