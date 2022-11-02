package pl.edu.pw.infstos.szsdsr.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.infstos.szsdsr.payments.penalty.PenaltyDTO;
import pl.edu.pw.infstos.szsdsr.payments.penalty.PenaltyService;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffDTO;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffSimplifiedDTO;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/payments")
public class PaymentsApiController implements PaymentsAPI {

    private final TariffService tariffService;
    private final PenaltyService penaltyService;

    public PaymentsApiController(
            @Autowired TariffService tariffService,
            @Autowired PenaltyService penaltyService
            ) {
        this.tariffService = tariffService;
        this.penaltyService = penaltyService;
    }

    @Override
    @GetMapping("tariff")
    public List<TariffSimplifiedDTO> getAllTariffs() {
        return tariffService.getAllTariffs();
    }

    @Override
    @PostMapping("tariff")
    public TariffDTO addTariff(@RequestBody @Valid TariffDTO tariff) {
        return tariffService.addTariff(tariff);
    }

    @Override
    @PutMapping("tariff/{id}")
    public ResponseEntity<TariffDTO> updateTariff(@PathVariable Long id, @RequestBody @Valid TariffDTO tariff) {
        tariff.setId(id);

        Optional<TariffDTO> updatedTariff = tariffService.updateTariff(tariff);
        if (updatedTariff.isPresent()) {
            return ResponseEntity.of(updatedTariff);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("tariff/{id}")
    public ResponseEntity<TariffDTO> deleteTariff(@PathVariable Long id) {
        boolean removed = tariffService.deleteTariff(id);
        return new ResponseEntity<>(removed ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    @GetMapping("tariff/{id}")
    public ResponseEntity<TariffDTO> getTariff(@PathVariable Long id) {
        return ResponseEntity.of(tariffService.getTariff(id));
    }

    @Override
    @GetMapping("penalty/{userId}")
    public List<PenaltyDTO> getAllPenalties(@PathVariable Long userId) {
        return penaltyService.getAllPenalties(userId);
    }

}
