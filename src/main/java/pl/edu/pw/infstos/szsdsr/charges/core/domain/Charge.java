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
    private Boolean isPayed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    protected Charge() {
    }

    public Charge(Long id, BigDecimal amount, Boolean isPayed, Payment payment) {
        this.id = id;
        this.amount = amount;
        this.isPayed = isPayed;
        this.payment = payment;
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

    public Boolean getPayed() {
        return isPayed;
    }

    public void setPayed(Boolean payed) {
        isPayed = payed;
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
