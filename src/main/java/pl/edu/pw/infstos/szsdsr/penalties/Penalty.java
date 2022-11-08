package pl.edu.pw.infstos.szsdsr.penalties;

import pl.edu.pw.infstos.szsdsr.passings.Passing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private UUID userId;

    @OneToOne
    @JoinColumn(name = "passing_id", nullable = false)
    @NotNull
    private Passing passing;

    private String description;

    @NotNull
    private boolean paid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Passing getPassing() {
        return passing;
    }

    public void setPassing(Passing passing) {
        this.passing = passing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
