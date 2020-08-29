package ir.maktab.controller;

import ir.maktab.model.entity.Course;
import ir.maktab.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/teacherCourseList/{email}",consumes = "application/json", produces = "application/json" )
    public List<Course> getTeacherCourses(@PathVariable("email") String email) {

        try {
            return teacherService.getTeacherCourses(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
