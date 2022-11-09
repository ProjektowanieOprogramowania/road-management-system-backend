package pl.edu.pw.infstos.szsdsr.driving.passings;

import pl.edu.pw.infstos.szsdsr.localization.Localization;
import pl.edu.pw.infstos.szsdsr.driving.vehicle.Vehicle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Passing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private boolean payable;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @ManyToOne
    private Localization localization;

    @NotNull
    @OneToOne
    private Vehicle vehicle;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPayable() {
        return payable;
    }

    public void setPayable(boolean payable) {
        this.payable = payable;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
