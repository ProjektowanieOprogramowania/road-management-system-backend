package pl.edu.pw.infstos.szsdsr.tariffs.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.TariffDTO;
import pl.edu.pw.infstos.szsdsr.generated.models.TariffSimplifiedDTO;
import pl.edu.pw.infstos.szsdsr.tariffs.repositories.TariffRepository;
import pl.edu.pw.infstos.szsdsr.tariffs.Tariff;

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

    public List<TariffSimplifiedDTO> getAllTariffs() {
        List<Tariff> tariffs = tariffRepository.findAll();
        return tariffs.stream().map(this::tariffToSimplifiedDto).collect(Collectors.toList());
    }

    public TariffDTO addTariff(TariffDTO tariffDto) {
        Tariff tariff = dtoToTariff(tariffDto);
        tariff.setId(null);
        Tariff newTariff = tariffRepository.save(tariff);
        return tariffToDto(newTariff);
    }

    public Optional<TariffDTO> updateTariff(TariffDTO tariffDto) {
        Tariff tariff = dtoToTariff(tariffDto);
        if (tariffRepository.existsById(tariff.getId())) {
            Tariff newTariff = tariffRepository.save(tariff);
            return Optional.of(tariffToDto(newTariff));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteTariff(Long id) {
        if (tariffRepository.existsById(id)) {
            tariffRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<TariffDTO> getTariff(Long id) {
        return tariffRepository.findById(id).map(this::tariffToDto);
    }

    private TariffSimplifiedDTO tariffToSimplifiedDto(Tariff tariff) {
        return modelMapper.map(tariff, TariffSimplifiedDTO.class);
    }

    private TariffDTO tariffToDto(Tariff tariff) {
        return modelMapper.map(tariff, TariffDTO.class);
    }

    private Tariff dtoToTariff(TariffDTO tariffDto) {
        return modelMapper.map(tariffDto, Tariff.class);
    }
}
