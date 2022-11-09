package pl.edu.pw.infstos.szsdsr.charges.core.domain;

import pl.edu.pw.infstos.szsdsr.generated.models.ChargeTypeDTO;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Charge {

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount;
    @NotNull
    private ChargeTypeDTO chargeType;
    private Boolean paid;

    @NotNull
    private UUID userId;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    protected Charge() {
    }

    public Charge(AppUser user, BigDecimal amount) {
        this.userId = user.getUuid();
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public ChargeTypeDTO getChargeType() {
        return chargeType;
    }

    public void setChargeType(ChargeTypeDTO chargeType) {
        this.chargeType = chargeType;
    }
}
