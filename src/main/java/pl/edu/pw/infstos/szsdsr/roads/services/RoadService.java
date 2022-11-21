package pl.edu.pw.infstos.szsdsr.roads.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadDTO;
import pl.edu.pw.infstos.szsdsr.localization.Localization;
import pl.edu.pw.infstos.szsdsr.localization.repositories.LocalizationRepository;
import pl.edu.pw.infstos.szsdsr.roads.domain.Road;
import pl.edu.pw.infstos.szsdsr.roads.domain.RoadNode;
import pl.edu.pw.infstos.szsdsr.roads.domain.RoadSegment;
import pl.edu.pw.infstos.szsdsr.roads.repositories.RoadNodeRepository;
import pl.edu.pw.infstos.szsdsr.roads.repositories.RoadRepository;
import pl.edu.pw.infstos.szsdsr.roads.repositories.RoadSegmentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoadService {
    private final RoadRepository roadRepository;
    private final ObjectMapper objectMapper;
    private final RoadSegmentRepository roadSegmentRepository;
    private final RoadNodeRepository roadNodeRepository;
    private final LocalizationRepository localizationRepository;

    public RoadService(@Autowired RoadRepository roadRepository,
                       @Autowired RoadNodeRepository roadNodeRepository,
                       @Autowired RoadSegmentRepository roadSegmentRepository,
                       @Autowired LocalizationRepository localizationRepository,
                       @Autowired ObjectMapper objectMapper) {
        this.roadRepository = roadRepository;
        this.roadNodeRepository = roadNodeRepository;
        this.roadSegmentRepository = roadSegmentRepository;
        this.localizationRepository = localizationRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public RoadDTO addRoad(RoadDTO roadDto) {
        Road road = dtoToRoad(roadDto);

        List<RoadSegment> segments = road.getSegments();
        List<RoadNode> uniqueNodes = new ArrayList<>();
        List<Localization> uniqueLocalizations = new ArrayList<>();
        Localization localization;

        if (segments != null) {
            for (RoadSegment segment : segments) {
                List<RoadNode> nodes = new ArrayList<>();
                nodes.add(segment.getStartNode());
                nodes.add(segment.getEndNode());
                for (RoadNode node : nodes) {
                    if (!uniqueNodes.contains(node)) {
                        uniqueNodes.add(node);
                        localization = node.getLocalization();
                        if (!uniqueLocalizations.contains(localization)) {
                            uniqueLocalizations.add(localization);
                            localization = localizationRepository.save(localization);
                        }
                        node = roadNodeRepository.save(node);
                    }
                }
            }

            for (RoadSegment rs : segments) {
                if (rs.getId() == null || !roadSegmentRepository.existsById(rs.getId())) {
                    rs = roadSegmentRepository.save(rs);
                }
            }
        }

        road.setId(null);
        Road newRoad = roadRepository.save(road);
        return roadToDto(newRoad);
    }

    public Optional<RoadDTO> getRoad(Long id) {
        return roadRepository.findById(id).map(this::roadToDto);
    }

    public Optional<RoadDTO> updateRoad(RoadDTO roadDto) {
        Road road = dtoToRoad(roadDto);
        if (roadRepository.existsById(road.getId())) {
            Road newRoad = roadRepository.save(road);
            return Optional.of(roadToDto(newRoad));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteRoad(Long id) {
        if (roadRepository.existsById(id)) {
            roadRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<String> findNamesByIds(List<Long> ids) {
        return roadRepository.findNamesByIds(ids);
    }

    public List<Road> findByIds(List<Long> ids) {
        return roadRepository.findByIds(ids);
    }

    private RoadDTO roadToDto(Road road) {
        return objectMapper.convertValue(road, RoadDTO.class);
    }

    private Road dtoToRoad(RoadDTO roadDto) {
        return objectMapper.convertValue(roadDto, Road.class);
    }

    public List<RoadDTO> getAllRoads() {
        return roadRepository.findAll()
                .stream()
                .map(this::roadToDto)
                .collect(Collectors.toList());
    }
}
