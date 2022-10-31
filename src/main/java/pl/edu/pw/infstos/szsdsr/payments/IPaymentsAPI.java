package pl.edu.pw.infstos.szsdsr.payments;

import org.springframework.http.ResponseEntity;
import pl.edu.pw.infstos.szsdsr.payments.penalty.Penalty;
import pl.edu.pw.infstos.szsdsr.payments.tariff.Tariff;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffDTO;

import java.util.List;

public interface IPaymentsAPI {

    List<TariffDTO> getAllTariffs();
    Tariff addTariff(Tariff tariff);
    ResponseEntity<Tariff> updateTariff(Tariff tariff);
    ResponseEntity<Tariff> deleteTariff(Long id);
    ResponseEntity<Tariff> getTariff(Long id);

    List<Penalty> getAllPenalties(Long userId);

}
