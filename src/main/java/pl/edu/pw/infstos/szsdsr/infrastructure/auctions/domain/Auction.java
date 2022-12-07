package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain;

import pl.edu.pw.infstos.szsdsr.localization.Localization;

import javax.persistence.*;
import java.util.List;

@Entity
public class Auction {

    @Id
    @GeneratedValue
    private Long id;
    private Boolean isOpen;
    private Double staringPrice;
    private String name;
    private String description;
    private Integer number;
    private Long dueDate;

    @OneToOne
    @JoinColumn(name = "localization_id")
    private Localization localization;

    @OneToMany(mappedBy = "auction")
    private List<AuctionOffer> offers = null;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean open) {
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

    public List<AuctionOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<AuctionOffer> offers) {
        this.offers = offers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
