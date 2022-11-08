package pl.edu.pw.infstos.szsdsr.charges.passings.domain;

import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;

import javax.persistence.*;

@Entity
public class PassingCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "chargeId")
    private Charge charge;

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
}
