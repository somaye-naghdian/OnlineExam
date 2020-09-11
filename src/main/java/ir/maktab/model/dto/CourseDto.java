package ir.maktab.model.dto;

import ir.maktab.model.entity.User;

import java.util.List;

public class CourseDto {

    private Long id;

    private String courseTitle;

    private String classification;

    private List<User> userList;


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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", classification='" + classification + '\'' +
                ", userList=" + userList +
                '}';
    }
}

