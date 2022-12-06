package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain;

import pl.edu.pw.infstos.szsdsr.generated.models.CurrencyDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class AuctionOffer {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private UUID userId;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @NotNull
    private CurrencyDTO currency = CurrencyDTO.PLN;

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

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}