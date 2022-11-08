package pl.edu.pw.infstos.szsdsr.users.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid = UUID.randomUUID();

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
