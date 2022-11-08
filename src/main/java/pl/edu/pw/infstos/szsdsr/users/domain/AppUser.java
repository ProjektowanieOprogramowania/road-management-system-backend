package pl.edu.pw.infstos.szsdsr.users.domain;

import javax.persistence.Column;
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
    @Column(name = "login", unique = true, nullable = false)
    private String login;
//    @OneToOne(mappedBy = "user")
//    private Subscription subscription;

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
