package ir.maktab.controller;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.*;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseController {


    private UserService userService;
    private CourseService courseService;
    private TeacherService teacherService;
    private ClassificationService classificationService;
    private Mapper mapper;


    @Autowired
    public CourseController(UserService userService, CourseService courseService,
                            TeacherService teacherService, ClassificationService classificationService
                            , Mapper mapper) {
        this.userService = userService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.classificationService = classificationService;
        this.mapper=mapper;
    }


    @RequestMapping(value = "addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse(Model model) {
        model.addAttribute("course", new CourseDto());
        ModelAndView modelAndView = new ModelAndView("addCourse");
        List<Classification> allClassification = classificationService.getAllClassification();
        modelAndView.addObject("allClassification", allClassification);
        return modelAndView;
    }

    @RequestMapping(value = "addCourseProcess", method = RequestMethod.GET)
    public ModelAndView addCourseProcess(@ModelAttribute("course") CourseDto courseDto,
                                         @RequestParam("classification") String title) {
        String courseTitle = courseDto.getCourseTitle();
        String classification = courseDto.getClassification();
        Classification classificationDto = classificationService.getClassificationByTitle(classification);
        Course course = new Course();
        course.setCourseTitle(courseTitle);
        course.setClassification(classificationDto);
        try {
            courseService.save(course);

        } catch (CourseAlreadyExist e) {
            new ModelAndView("error", "errorMsg", e.getMessage());
        }
        String message = " Course " + courseDto.getCourseTitle() + " successfully add";
        ModelAndView modelAndView = new ModelAndView("simpleMessage", "message", message);
        return modelAndView;
    }
    @RequestMapping(value = "getCoursePage", method = RequestMethod.GET)
    public ModelAndView getCoursePage(@ModelAttribute("user") String email) {
        User user = userService.findUserByEmail(email);
        try {
            List<Course> courseList = userService.getUserCourses(user.getEmail());
            System.out.println(courseList);
            return new ModelAndView("teacherCourses", "courseList", courseList);
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("teacherCourses");
        }

    }

}
