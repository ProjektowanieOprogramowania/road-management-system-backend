package pl.edu.pw.infstos.szsdsr.charges.passings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class SubscriptionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private Double costPerMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(Double costPerMonth) {
        this.costPerMonth = costPerMonth;
    }
}
