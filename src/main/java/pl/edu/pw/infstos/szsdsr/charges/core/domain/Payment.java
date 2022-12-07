package pl.edu.pw.infstos.szsdsr.charges.core.domain;

import pl.edu.pw.infstos.szsdsr.generated.models.PaymentMethodDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private OffsetDateTime dateTime;

    @NotNull
    private PaymentMethodDTO paymentMethod;

    protected Payment() {
    }

    public Payment(Long id, BigDecimal amount, OffsetDateTime dateTime, PaymentMethodDTO paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
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


    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
