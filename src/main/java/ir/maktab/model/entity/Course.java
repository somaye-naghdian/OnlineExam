package ir.maktab.model.entity;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    private String courseTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    private Classification classification;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private List<Exam> examList;

    @ManyToMany(mappedBy = "courseList")
    @LazyCollection(LazyCollectionOption.FALSE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(courseTitle, course.courseTitle) &&
                Objects.equals(classification, course.classification) &&
                Objects.equals(examList, course.examList) &&
                Objects.equals(userList, course.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseTitle, classification, examList, userList);
    }
}
