package ir.maktab.model.entity;

import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "status")
    private StatusType status;

   @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
   //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> courseList;

    public User(String name, String family, String email, String password, UserRole role) {
        this.name = name;
        this.family = family;
        this.email = email;
        this.password = password;
        this.role = role;

    }

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

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
