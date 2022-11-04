package pl.edu.pw.infstos.szsdsr.localization.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.LocalizationDTO;
import pl.edu.pw.infstos.szsdsr.localization.Localization;
import pl.edu.pw.infstos.szsdsr.localization.repositories.LocalizationRepository;

import java.util.Optional;

@Service
public class LocalizationService {

    private final LocalizationRepository localizationRepository;
    private final ObjectMapper objectMapper;

    public LocalizationService(@Autowired LocalizationRepository localizationRepository,
                               @Autowired ObjectMapper objectMapper) {
        this.localizationRepository = localizationRepository;
        this.objectMapper = objectMapper;
    }

    public LocalizationDTO addLocalization(LocalizationDTO localizationDto) {
        Localization localization = dtoToLocalization(localizationDto);
        localization.setId(null);
        Localization newLocalization = localizationRepository.save(localization);
        return localizationToDto(newLocalization);
    }

    public Optional<LocalizationDTO> getLocalization(Long id) {
        return localizationRepository.findById(id).map(this::localizationToDto);
    }

    public Optional<LocalizationDTO> updateLocalization(LocalizationDTO localizationDto) {
        Localization localization = dtoToLocalization(localizationDto);
        if (localizationRepository.existsById(localization.getId())) {
            Localization newLocalization = localizationRepository.save(localization);
            return Optional.of(localizationToDto(newLocalization));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteLocalization(Long id) {
        if (localizationRepository.existsById(id)) {
            localizationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private LocalizationDTO localizationToDto(Localization localization) {
        return objectMapper.convertValue(localization, LocalizationDTO.class);
    }

    private Localization dtoToLocalization(LocalizationDTO localizationDto) {
        return objectMapper.convertValue(localizationDto, Localization.class);
    }
}
