package ir.maktab.model.entity;

import ir.maktab.util.UserRole;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Student extends User {

    @ManyToMany(mappedBy = "examiners",fetch = FetchType.LAZY)
    private List<Exam> exams;

    public Student(User user) {
        super(user.getName(), user.getFamily(), user.getEmail(), user.getPassword(), user.getRole());
        this.setRole(UserRole.STUDENT);
    }

    public Student() {
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
