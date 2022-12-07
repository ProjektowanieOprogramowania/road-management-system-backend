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

    private Integer score;

    private Long auctionId;

    @NotNull
    private CurrencyDTO currency = CurrencyDTO.PLN;

    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
