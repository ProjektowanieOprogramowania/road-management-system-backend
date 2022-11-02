package pl.edu.pw.infstos.szsdsr.payments;

import org.springframework.http.ResponseEntity;
import pl.edu.pw.infstos.szsdsr.payments.penalty.PenaltyDTO;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffDTO;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffSimplifiedDTO;

import java.util.List;

public interface IPaymentsAPI {

    List<TariffSimplifiedDTO> getAllTariffs();
    TariffDTO addTariff(TariffDTO tariff);
    ResponseEntity<TariffDTO> updateTariff(Long id, TariffDTO tariff);
    ResponseEntity<TariffDTO> deleteTariff(Long id);
    ResponseEntity<TariffDTO> getTariff(Long id);

    List<PenaltyDTO> getAllPenalties(Long userId);

}
