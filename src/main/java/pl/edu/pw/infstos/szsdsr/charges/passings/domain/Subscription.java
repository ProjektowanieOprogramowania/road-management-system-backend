package pl.edu.pw.infstos.szsdsr.charges.passings.domain;

import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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

    @ElementCollection
    public List<Long> roadsIds;
    @OneToOne
    private Charge charge;

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

    public List<Long> getRoadIds() {
        return roadsIds;
    }

    public void setRoadIds(List<Long> roadIds) {
        this.roadsIds = roadIds;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }
}
