package pl.edu.pw.infstos.szsdsr.tenders.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.infstos.szsdsr.tenders.domain.TenderOffer;

@Repository
public interface TenderOfferRepo extends JpaRepository<TenderOffer, Long> { }
