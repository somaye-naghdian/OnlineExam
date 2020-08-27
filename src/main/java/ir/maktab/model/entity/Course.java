package ir.maktab.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courseTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    private Classification classification;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> userList;

    public Course() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

        public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }


}
