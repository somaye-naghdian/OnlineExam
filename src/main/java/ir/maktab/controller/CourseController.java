package ir.maktab.controller;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.AdminService;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import ir.maktab.util.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CourseController {


    private UserService userService;
    private CourseService courseService;
    private AdminService adminService;
    private ClassificationService classificationService;
    private Mapper mapper=new Mapper();


    @Autowired
    public CourseController(UserService userService, CourseService courseService,
                            AdminService adminService, ClassificationService classificationService) {
        this.userService = userService;
        this.courseService = courseService;
        this.adminService = adminService;
        this.classificationService = classificationService;

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




}
