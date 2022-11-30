package pl.edu.pw.infstos.szsdsr.tenders.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.infstos.szsdsr.tenders.domain.Tender;

@Repository
public interface TenderRepo extends JpaRepository<Tender, Long> { }
