package ir.maktab.controller;

import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import ir.maktab.util.Mapper;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;
    private CourseService courseService;
    private Mapper mapper ;

    @Autowired
    public UserController(UserService userService,CourseService courseService
    ,Mapper mapper) {
        this.userService = userService;
        this.courseService=courseService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }



    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String showError(String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject(message);
        return "error";
    }
    @RequestMapping(value = "addUserToCourse", method = RequestMethod.GET)
    public ModelAndView addUserToCourse(Model model) {
        model.addAttribute("user", new UserDto());
        ModelAndView modelAndView = new ModelAndView("course_user");
        List<Course> allCourse = courseService.getAllCourse();
        List<CourseDto> courseDtoList = mapper.convertToCourseDtoList(allCourse);
        modelAndView.addObject("allCourse", courseDtoList);
        return modelAndView;
    }


    @RequestMapping(value = "userOfCourse", method = RequestMethod.GET)
    public ModelAndView getUserOfCourse(Model model) {
        model.addAttribute("course", new CourseDto());
        ModelAndView modelAndView = new ModelAndView("showUserOfCourse");
        return modelAndView;
    }

    @RequestMapping(value = "userOfCourseProcess", method = RequestMethod.POST)
    public ModelAndView getUserOfCourseProcess(@ModelAttribute("course") CourseDto courseDto) {
        ModelAndView modelAndView =new ModelAndView("showUserOfCourse");
        try {
            Set<User> studentOfCourse = courseService.getStudentOfCourse(courseDto.getCourseTitle());
            modelAndView = new ModelAndView("showUserOfCourse");
            modelAndView.addObject("userOfCourse", studentOfCourse);
            return modelAndView;
        } catch (Exception e) {
            new ModelAndView("error", "errorMsg", e.getMessage());
        }
        return modelAndView;
    }
}
