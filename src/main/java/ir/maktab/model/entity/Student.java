package ir.maktab.model.entity;

import ir.maktab.util.UserRole;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class Student extends User {

    @ManyToMany(mappedBy = "studentList")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Exam> exams;

    @ElementCollection
    @MapKeyJoinColumn(name = "exam_id")
    @Column(name = "exam_score")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<Exam, Double> scores;


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

    public Map<Exam, Double> getScores() {
        return scores;
    }

    public void setScores(Map<Exam, Double> scores) {
        this.scores = scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(exams, student.exams) &&
                Objects.equals(scores, student.scores);
    }

}
