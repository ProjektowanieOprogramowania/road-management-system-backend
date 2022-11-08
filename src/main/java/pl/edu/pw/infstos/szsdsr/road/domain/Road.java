package pl.edu.pw.infstos.szsdsr.road.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Road {

    @Id
    @GeneratedValue
    private Long id;

}
