package ir.maktab.model.entity;

import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String family;
    @Column(unique = true)
    private String email;
    private UserRole role;
    private String password;
    @Column(name = "enabled")
    private StatusType status;

    public User() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusType isEnabled() {
        return status;
    }

    public void setStatus(StatusType enabled) {
        this.status = enabled;
    }

    public StatusType getStatus() {
        return status;
    }
}
