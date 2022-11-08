package pl.edu.pw.infstos.szsdsr.charges.passings.domain;

import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;
import pl.edu.pw.infstos.szsdsr.passings.Passing;

import javax.persistence.*;

@Entity
public class PassingCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charge_id", nullable = false)
    private Charge charge;
    @OneToOne()
    @JoinColumn(name = "passing_id", nullable = false)
    private Passing passing;

    public PassingCharge(Charge charge, Passing passing) {
        this.charge = charge;
        this.passing = passing;
    }

    protected PassingCharge() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Passing getPassing() {
        return passing;
    }

    public void setPassing(Passing passing) {
        this.passing = passing;
    }
}
