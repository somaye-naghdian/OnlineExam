package ir.maktab.model.entity;

import ir.maktab.util.UserRole;

import javax.persistence.Entity;

@Entity
public class Student extends User {

    public Student() {
        this.setRole(UserRole.STUDENT);
    }
}
