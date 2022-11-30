package pl.edu.pw.infstos.szsdsr.tenders.domain;

import javax.persistence.*;

@Entity
public class TenderOffer {

    @Id
    @GeneratedValue
    private Long id;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }
}
