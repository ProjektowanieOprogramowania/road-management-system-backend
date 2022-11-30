package pl.edu.pw.infstos.szsdsr.auctions.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.infstos.szsdsr.auctions.domain.Auction;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long> { }
