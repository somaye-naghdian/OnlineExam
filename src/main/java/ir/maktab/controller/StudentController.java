package ir.maktab.controller;

import ir.maktab.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
    private CourseService courseService;

    @Autowired
    public StudentController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/addStudentToCourse", method = RequestMethod.GET)
    public ModelAndView addStudentToCourse(@RequestParam("email")String email
            , @RequestParam("course") String courseTitle) {

        courseService.addUserToCourse(courseTitle, email);
      ModelAndView modelAndView = new ModelAndView("simpleMessage");
        modelAndView.addObject("message", "student successfully added to  " + courseTitle);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteStudentFromCourse", method = RequestMethod.GET)
    public ModelAndView deleteStudentFromCourse(@RequestParam("email")String email
            , @RequestParam("course") String courseTitle) {
        courseService.deleteStudentFromCourse(courseTitle, email);
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        modelAndView.addObject("message", "student successfully delete from  " + courseTitle);
        return modelAndView;
    }
}
