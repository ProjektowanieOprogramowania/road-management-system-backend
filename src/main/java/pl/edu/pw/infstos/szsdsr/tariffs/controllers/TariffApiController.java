package pl.edu.pw.infstos.szsdsr.tariffs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.infstos.szsdsr.generated.api.TariffsApi;
import pl.edu.pw.infstos.szsdsr.generated.models.TariffDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.TariffSimplifiedDTO;
import pl.edu.pw.infstos.szsdsr.tariffs.services.TariffService;

import java.util.List;
import java.util.Optional;

@RestController
public class TariffApiController implements TariffsApi {

    private final TariffService tariffService;

    public TariffApiController(@Autowired TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public ResponseEntity<TariffDTO> addTariff(TariffDTO tariffDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tariffService.addTariff(tariffDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteTariff(Long tariffId) {
        if (tariffService.deleteTariff(tariffId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TariffSimplifiedDTO>> getAllTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariffs());
    }

    @Override
    public ResponseEntity<TariffDTO> getTariff(Long tariffId) {
        Optional<TariffDTO> tariffDTO = tariffService.getTariff(tariffId);
        if (tariffDTO.isPresent()) {
            return ResponseEntity.ok(tariffDTO.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<TariffDTO> updateTariff(Long tariffId, TariffDTO tariffDTO) {
        try {
            tariffDTO.setId(tariffId);
            Optional<TariffDTO> tdto = tariffService.updateTariff(tariffDTO);
            if (tdto.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(tdto.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
