package pl.edu.pw.infstos.szsdsr.penalties;

import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;
import pl.edu.pw.infstos.szsdsr.passings.Passing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "passing_id", nullable = false)
    @NotNull
    private Passing passing;

    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "charge_id", nullable = false)
    @NotNull
    private Charge charge;

    @NotNull
    private boolean paid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passing getPassing() {
        return passing;
    }

    public void setPassing(Passing passing) {
        this.passing = passing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }
}
