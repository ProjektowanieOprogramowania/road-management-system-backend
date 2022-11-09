package pl.edu.pw.infstos.szsdsr.road.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Road {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Double subscriptionPriceForOneDay = 0.0;

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

    public Double getSubscriptionPriceForOneDay() {
        return subscriptionPriceForOneDay;
    }

    public void setSubscriptionPriceForOneDay(Double subscriptionPriceForOneDay) {
        this.subscriptionPriceForOneDay = subscriptionPriceForOneDay;
    }
}
