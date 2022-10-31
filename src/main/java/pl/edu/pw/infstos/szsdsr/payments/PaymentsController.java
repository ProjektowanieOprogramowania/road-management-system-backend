package pl.edu.pw.infstos.szsdsr.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.infstos.szsdsr.payments.penalty.Penalty;
import pl.edu.pw.infstos.szsdsr.payments.tariff.Tariff;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffDTO;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffService;

import javax.validation.Valid;
import java.util.List;

// TODO: dodać możliwość dodawania cen do Tariff

@RestController
@RequestMapping(path = "api/v1/payments")
public class PaymentsController implements IPaymentsAPI {

    private final TariffService tariffService;

    public PaymentsController(
            @Autowired TariffService tariffService
    ) {
        this.tariffService = tariffService;
    }

    @Override
    @GetMapping("tariff")
    public List<TariffDTO> getAllTariffs() {
        return tariffService.getAllTariffs();
    }

    @Override
    @PostMapping("tariff")
    public Tariff addTariff(@RequestBody @Valid Tariff tariff) {
        return tariffService.addTariff(tariff);
    }

    @Override
    @PutMapping("tariff/{id}")
    public ResponseEntity<Tariff> updateTariff(Tariff tariff) {
        // TODO
        return null;
    }

    @Override
    @DeleteMapping("tariff/{id}")
    public ResponseEntity<Tariff> deleteTariff(@PathVariable Long id) {
        boolean removed = tariffService.deleteTariff(id);
        return new ResponseEntity<>(removed ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("tariff/{id}")
    public ResponseEntity<Tariff> getTariff(@PathVariable Long id) {
        return ResponseEntity.of(tariffService.getTariff(id));
    }

    @Override
    public List<Penalty> getAllPenalties(Long userId) {
        // TODO
        return null;
    }

}
