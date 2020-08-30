package ir.maktab.model.entity;

import ir.maktab.util.UserRole;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Teacher extends User {

    public Teacher(User user) {
        super(user.getName(), user.getFamily(), user.getEmail(), user.getPassword(), user.getRole());
        this.setRole(UserRole.TEACHER);
    }

    public Teacher() {
    }

}
