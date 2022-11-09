package pl.edu.pw.infstos.szsdsr.charges.passings.domain;

import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pw.infstos.szsdsr.road.domain.Road;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private UUID subscriberId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate subscriptionFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate subscriptionTo;
    @ManyToMany
    private Set<Road> roads;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(UUID subscriberId) {
        this.subscriberId = subscriberId;
    }

    public LocalDate getSubscriptionFrom() {
        return subscriptionFrom;
    }

    public void setSubscriptionFrom(LocalDate subscriptionFrom) {
        this.subscriptionFrom = subscriptionFrom;
    }

    public LocalDate getSubscriptionTo() {
        return subscriptionTo;
    }

    public void setSubscriptionTo(LocalDate subscriptionTo) {
        this.subscriptionTo = subscriptionTo;
    }

    public Set<Road> getRoads() {
        return roads;
    }

    public void setRoads(Set<Road> roads) {
        this.roads = roads;
    }
}
