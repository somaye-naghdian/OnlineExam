package ir.maktab.model.entity;

import ir.maktab.util.UserRole;

import javax.persistence.Entity;

@Entity
public class Teacher extends User{

    private String teacherNumber;

    public Teacher(){
        this.setRole(UserRole.TEACHER);
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
}
