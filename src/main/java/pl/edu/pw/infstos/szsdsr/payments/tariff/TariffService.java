package pl.edu.pw.infstos.szsdsr.payments.tariff;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TariffService {

    private final TariffRepository tariffRepository;

    private final ModelMapper modelMapper;

    public TariffService(
            @Autowired TariffRepository tariffRepository,
            @Autowired ModelMapper modelMapper
    ) {
        this.tariffRepository = tariffRepository;

        this.modelMapper = modelMapper;
    }

    public List<TariffDTO> getAllTariffs() {
        List<Tariff> tariffs = tariffRepository.findAll();
        return tariffs.stream().map(t -> modelMapper.map(t, TariffDTO.class)).collect(Collectors.toList());
    }

    public Tariff addTariff(Tariff tariff) {
        tariff.setId(null);
        return tariffRepository.save(tariff);
    }

    public Tariff updateTariff(Tariff tariff) {
        // TODO
        return null;
    }

    public boolean deleteTariff(Long id) {
        if (tariffRepository.existsById(id)) {
            tariffRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Tariff> getTariff(Long id) {
        return tariffRepository.findById(id);
    }

}
