package ir.maktab.model.dto;

import ir.maktab.model.entity.Course;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;

import java.util.List;

public class UserDto {

    private Integer id;
    private String name;
    private String family;
    private String email;
    private UserRole role;
    private String password;
    private StatusType enabled;

    private List<Course> courseList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusType isEnabled() {
        return enabled;
    }

    public void setEnabled(StatusType enabled) {
        this.enabled = enabled;
    }

    public StatusType getEnabled() {
        return enabled;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}
