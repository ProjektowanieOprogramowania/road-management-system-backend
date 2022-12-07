package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain;

import pl.edu.pw.infstos.szsdsr.generated.models.CurrencyDTO;
import pl.edu.pw.infstos.szsdsr.localization.Localization;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Auction {

    @Id
    @GeneratedValue
    private Long id;
    private Boolean isOpen;
    private Double staringPrice;
    private CurrencyDTO staringPriceCurrency;
    private String name;
    private String description;
    private Integer number;
    private Long dueDate;

    @OneToOne
    @JoinColumn(name = "localization_id")
    private Localization localization;

    @OneToMany(mappedBy = "auctionId")
    private List<AuctionOffer> offers = null;

    public Auction() {
    }

    public Auction(Double staringPrice,
                   CurrencyDTO staringPriceCurrency,
                   String name,
                   String description,
                   Integer number,
                   Long dueDate,
                   Localization localization
    ) {
        this.staringPrice = staringPrice;
        this.staringPriceCurrency = staringPriceCurrency;
        this.name = name;
        this.description = description;
        this.number = number;
        this.dueDate = dueDate;
        this.localization = localization;
    }

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

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public CurrencyDTO getStaringPriceCurrency() {
        return staringPriceCurrency;
    }

    public void setStaringPriceCurrency(CurrencyDTO staringPriceCurrency) {
        this.staringPriceCurrency = staringPriceCurrency;
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
        if(offers != null) {
            offers.forEach(offer -> offer.setAuctionId(this.id));
        }
        this.offers = offers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
