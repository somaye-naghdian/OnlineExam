package ir.maktab.model.entity;

import ir.maktab.util.UserRole;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    private String teacherNumber;

    public Admin() {
        this.setRole(UserRole.ADMIN);
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
}
