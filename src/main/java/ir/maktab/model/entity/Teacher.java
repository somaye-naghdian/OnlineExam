package ir.maktab.model.entity;

import ir.maktab.util.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher extends User {

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<Exam> exams;




    public Teacher(User user) {
        super(user.getName(), user.getFamily(), user.getEmail(), user.getPassword(), user.getRole());
        this.setRole(UserRole.TEACHER);
    }

    public Teacher() {
    }


    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
