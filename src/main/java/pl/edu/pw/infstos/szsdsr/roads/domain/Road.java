package pl.edu.pw.infstos.szsdsr.roads.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Road {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Double subscriptionPriceForOneDay = 0.0;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RoadSegment> segments;

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

    public List<RoadSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<RoadSegment> segments) {
        this.segments = segments;
    }
}
