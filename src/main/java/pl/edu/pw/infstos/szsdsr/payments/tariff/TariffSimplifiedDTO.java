package pl.edu.pw.infstos.szsdsr.payments.tariff;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TariffSimplifiedDTO {

    private Long id;

    @NotNull
    private Boolean active;

    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
