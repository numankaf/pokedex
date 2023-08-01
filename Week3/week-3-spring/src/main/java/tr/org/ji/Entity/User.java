package tr.org.ji.Entity;

import jakarta.persistence.*;
import tr.org.ji.base.BaseEntity;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @Column(name = "USERNAME", length = 255, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
