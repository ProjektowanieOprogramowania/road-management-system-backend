package pl.edu.pw.infstos.szsdsr.tolls;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class SubscriptionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private Double costPerMonth;


}
