package ir.maktab.controller;

import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.AdminService;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    private UserService userService;
    private CourseService courseService;
    private AdminService adminService;
    private ClassificationService classificationService;
    private Mapper mapper;

    @Autowired
    public AdminController(UserService userService, CourseService courseService,
                           AdminService adminService, ClassificationService classificationService
                          , Mapper mapper) {
        this.userService = userService;
        this.courseService = courseService;
        this.adminService = adminService;
        this.classificationService = classificationService;
        this.mapper=mapper;
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "adminDashboard";
    }

    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView("showAllUser");
        List<User> userList = userService.showAllUser();
        List<UserDto> userDtoList = mapper.convertEntityListToDto(userList);
        modelAndView.addObject("userList", userDtoList);
        return modelAndView;
    }


    @RequestMapping(value = "getCourseList", method = RequestMethod.GET)
    public ModelAndView getCourseList() {
        ModelAndView modelAndView = new ModelAndView("showAllCourse");
        List<Course> allCourse = courseService.getAllCourse();
        List<CourseDto> courseDtoList = mapper.convertCourseToDtoList(allCourse);
        modelAndView.addObject("allCourse", courseDtoList);
        return modelAndView;
    }


}

