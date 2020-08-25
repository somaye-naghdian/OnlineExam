package ir.maktab.model.dto;

import ir.maktab.model.entity.User;

import java.util.List;

public class CourseDto {

    private Integer id;

    private String courseTitle;

    private String classification;

    private List<User> userList;


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

