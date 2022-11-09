package pl.edu.pw.infstos.szsdsr.charges.core.domain;

import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Charge {

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private Boolean paid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    protected Charge() {
    }

    public Charge(AppUser user, BigDecimal amount) {
        this.user = user;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
