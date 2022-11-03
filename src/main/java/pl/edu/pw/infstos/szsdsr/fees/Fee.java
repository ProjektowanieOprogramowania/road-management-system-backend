package pl.edu.pw.infstos.szsdsr.fees;

import pl.edu.pw.infstos.szsdsr.tolls.Toll;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Double amount;

    private String type;

    @ManyToOne
    @JoinColumn(name="toll_id", nullable=false)
    private Toll toll;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
