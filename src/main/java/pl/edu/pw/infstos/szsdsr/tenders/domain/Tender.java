package pl.edu.pw.infstos.szsdsr.tenders.domain;

import pl.edu.pw.infstos.szsdsr.generated.models.LocalizationDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.TenderOfferDTO;
import pl.edu.pw.infstos.szsdsr.localization.Localization;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tender {

    @Id
    @GeneratedValue
    private Long id;
    private Boolean isOpen;
    private Double staringPrice;
    private String name;
    private Integer number;
    private Long dueDate;

    @OneToOne
    @JoinColumn(name = "localization_id")
    private Localization localization;

    @OneToMany(mappedBy = "tender")
    private List<TenderOffer> offers = null;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Double getStaringPrice() {
        return staringPrice;
    }

    public void setStaringPrice(Double staringPrice) {
        this.staringPrice = staringPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public List<TenderOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<TenderOffer> offers) {
        this.offers = offers;
    }
}
