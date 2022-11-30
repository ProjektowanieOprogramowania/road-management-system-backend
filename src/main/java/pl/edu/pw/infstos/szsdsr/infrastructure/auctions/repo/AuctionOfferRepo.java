package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.AuctionOffer;

@Repository
public interface AuctionOfferRepo extends JpaRepository<AuctionOffer, Long> { }
