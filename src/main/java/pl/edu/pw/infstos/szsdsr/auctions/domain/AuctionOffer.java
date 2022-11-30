package pl.edu.pw.infstos.szsdsr.auctions.domain;

import javax.persistence.*;

@Entity
public class AuctionOffer {

    @Id
    @GeneratedValue
    private Long id;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

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
}
