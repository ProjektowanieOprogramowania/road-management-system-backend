package pl.edu.pw.infstos.szsdsr.users.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "app_users")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    public AppUser(String login) {
        this.login = login;
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
