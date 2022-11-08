package pl.edu.pw.infstos.szsdsr.fees;

import pl.edu.pw.infstos.szsdsr.charges.passings.domain.PassingCharge;

import javax.persistence.*;

@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Double amount;

    private String type;

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
