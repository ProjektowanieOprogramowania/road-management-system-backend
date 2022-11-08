package pl.edu.pw.infstos.szsdsr.charges.passings.domain;

import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate subscriptionFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate subscriptionTo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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
}
