package pl.edu.pw.infstos.szsdsr.roads.domain;

import pl.edu.pw.infstos.szsdsr.tariffs.Tariff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class RoadSegment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private RoadNode startNode;

    @NotNull
    @ManyToOne
    private RoadNode endNode;

    @ManyToOne
    private Tariff tariff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoadNode getStartNode() {
        return startNode;
    }

    public void setStartNode(RoadNode startNode) {
        this.startNode = startNode;
    }

    public RoadNode getEndNode() {
        return endNode;
    }

    public void setEndNode(RoadNode endNode) {
        this.endNode = endNode;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
