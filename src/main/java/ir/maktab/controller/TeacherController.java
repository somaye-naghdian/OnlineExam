package ir.maktab.controller;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.TeacherService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    private UserService userService;

    @Autowired
    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/teacherCourseList/{email}", produces = "text/html" )
    public List<Course> getTeacherCourses(@PathVariable("email") String email) {

        try {
            User user = userService.findUserByEmail(email);
            List<Course> teacherCourseList = user.getCourseList();
//            List<Course> courseList = userService.getUserCourses(email);
            System.out.println(teacherCourseList);
            return teacherCourseList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
