package pl.edu.pw.infstos.szsdsr.infrastructure.auctions.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.domain.Auction;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long> { }
