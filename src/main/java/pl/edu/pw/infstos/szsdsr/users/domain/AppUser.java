package pl.edu.pw.infstos.szsdsr.users.domain;

import pl.edu.pw.infstos.szsdsr.charges.passings.domain.Subscription;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid = UUID.randomUUID();
//    @OneToOne(mappedBy = "user")
//    private Subscription subscription;

    public AppUser(Long id, UUID uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    protected AppUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
