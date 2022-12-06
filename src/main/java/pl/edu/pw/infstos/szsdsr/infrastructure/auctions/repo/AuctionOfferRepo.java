package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.AuctionOffer;

import java.util.Collection;
import java.util.List;

@Repository
public interface AuctionOfferRepo extends JpaRepository<AuctionOffer, Long> {
    @Query("select o from AuctionOffer o where o.auction.id = :auctionId")
    List<AuctionOffer> findByAuctionId(Long auctionId);
}
