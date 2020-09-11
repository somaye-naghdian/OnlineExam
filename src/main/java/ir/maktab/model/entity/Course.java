package ir.maktab.model.entity;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    private String courseTitle;
//, cascade = CascadeType.REMOVE
    @ManyToOne(fetch = FetchType.EAGER)
    private Classification classification;
//, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE}
    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private List<Exam> examList;
//, cascade = CascadeType.PERSIST
    @ManyToMany(mappedBy = "courseList", fetch = FetchType.LAZY)
    private List<User> userList;


    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String title) {
        this.courseTitle = title;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", classification=" + classification +
                '}';
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

}
