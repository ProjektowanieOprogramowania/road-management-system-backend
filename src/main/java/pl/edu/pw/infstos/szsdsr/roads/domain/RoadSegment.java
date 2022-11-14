package pl.edu.pw.infstos.szsdsr.roads.domain;

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

    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
