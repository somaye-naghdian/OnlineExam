package ir.maktab.controller;

import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Teacher;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.TeacherService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TeacherController {

    private UserService userService;
    private CourseService courseService;

    @Autowired
    public TeacherController(UserService userService,CourseService courseService) {
        this.userService = userService;
        this.courseService=courseService;
    }

//    @ResponseBody
//    @GetMapping(value = "/teacherCourseList/{email}", produces = "text/html")
//    public List<Course> getTeacherCourses(@PathVariable("email") String email) {
//
//        try {
//            User user = userService.findUserByEmail(email);
//            Teacher teacher =new Teacher(user);
//            List<Course> teacherCourseList = teacher.getCourseList();
////            List<Course> courseList = userService.getUserCourses(email);
//            System.out.println(teacherCourseList);
//            return teacherCourseList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    @RequestMapping(value = "/addTeacherToCourse", method = RequestMethod.GET)
    public ModelAndView addTeacherToCourse(@RequestParam("email")String email
            , @RequestParam("course") String courseTitle) {
        courseService.addTeacherToCourse(courseTitle, email);
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        modelAndView.addObject("message", "teacher successfully added to  " + courseTitle);
        return modelAndView;
    }
}
