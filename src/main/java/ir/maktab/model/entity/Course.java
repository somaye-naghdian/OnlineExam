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

    @ManyToMany(mappedBy = "courseList", fetch = FetchType.EAGER)
    private Set<User> userList;

    public Course() {
    }

    public void addUser(User user) {
    this.userList.add(user);
    user.getCourseList().add(this);
    }

    public  void removeUser(User user){
        this.userList.remove(user);
        user.getCourseList().remove(this);
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

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", classification=" + classification +
                ", userList=" + userList +
                '}';
    }
}
