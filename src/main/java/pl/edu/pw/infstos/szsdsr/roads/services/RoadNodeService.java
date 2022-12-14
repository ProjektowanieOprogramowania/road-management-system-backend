package pl.edu.pw.infstos.szsdsr.roads.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.generated.models.RoadNodeDTO;
import pl.edu.pw.infstos.szsdsr.roads.domain.RoadNode;
import pl.edu.pw.infstos.szsdsr.roads.repositories.RoadNodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoadNodeService {
    private final RoadNodeRepository roadNodeRepository;
    private final ObjectMapper objectMapper;

    public RoadNodeService(@Autowired RoadNodeRepository roadNodeRepository,
                           @Autowired ObjectMapper objectMapper) {
        this.roadNodeRepository = roadNodeRepository;
        this.objectMapper = objectMapper;
    }

    public RoadNodeDTO addRoadNode(RoadNodeDTO roadNodeDto) {
        RoadNode roadNode = dtoToRoadNode(roadNodeDto);
        roadNode.setId(null);
        RoadNode newRoadNode = roadNodeRepository.save(roadNode);
        return roadNodeToDto(newRoadNode);
    }

    public List<RoadNodeDTO> addAllRoadNodes(List<RoadNodeDTO> roadNodeDtos) {
        List<RoadNode> roadNodes = roadNodeDtos.stream()
                .map(this::dtoToRoadNode).toList();

        roadNodes.forEach(rn -> rn.setId(null));

        List<RoadNode> savedRoadNodes = roadNodeRepository.saveAll(roadNodes);
        return savedRoadNodes.stream().map(this::roadNodeToDto).toList();
    }

    public List<RoadNodeDTO> getAllRoadNodes() {
        return roadNodeRepository.findAll()
                .stream()
                .map(this::roadNodeToDto)
                .collect(Collectors.toList());
    }

    public Optional<RoadNodeDTO> getRoadNode(Long id) {
        return roadNodeRepository.findById(id).map(this::roadNodeToDto);
    }

    public Optional<RoadNodeDTO> updateRoadNode(RoadNodeDTO roadNodeDto) {
        // Updates everything except for localization
        RoadNode roadNode = dtoToRoadNode(roadNodeDto);
        Optional<RoadNode> maybeExisting = roadNodeRepository.findById(roadNode.getId());
        if (maybeExisting.isPresent()) {
            roadNode.setLocalization(maybeExisting.get().getLocalization());
            RoadNode newRoadNode = roadNodeRepository.save(roadNode);
            return Optional.of(roadNodeToDto(newRoadNode));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteRoadNode(Long id) {
        if (roadNodeRepository.existsById(id)) {
            roadNodeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private RoadNodeDTO roadNodeToDto(RoadNode roadNode) {
        return objectMapper.convertValue(roadNode, RoadNodeDTO.class);
    }

    private RoadNode dtoToRoadNode(RoadNodeDTO roadNodeDto) {
        return objectMapper.convertValue(roadNodeDto, RoadNode.class);
    }

}
