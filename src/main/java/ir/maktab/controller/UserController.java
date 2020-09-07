package ir.maktab.controller;

import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.User;
import ir.maktab.service.AdminService;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import ir.maktab.util.StatusType;
import ir.maktab.util.Mapper;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;
    private CourseService courseService;
    private AdminService adminService;
    private Mapper mapper;

    @Autowired
    public UserController(UserService userService, CourseService courseService
            , AdminService adminService, Mapper mapper) {
        this.userService = userService;
        this.courseService = courseService;
        this.adminService = adminService;
        this.mapper = mapper;
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
        List<UserDto> userDtoList = new ArrayList<>();
        model.addAttribute("user", new UserDto());
        ModelAndView modelAndView = new ModelAndView("course_user");
        List<Course> allCourse = courseService.getAllCourse();
        List<CourseDto> courseDtoList = mapper.convertCourseToDtoList(allCourse);
        modelAndView.addObject("allCourse", courseDtoList);
        modelAndView.addObject("students", userService.findAllStudents());
        modelAndView.addObject("teachers",userService.findAllTeachers());
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
        ModelAndView modelAndView = new ModelAndView("showUserOfCourse");
        try {
            List<Student> studentOfCourse = courseService.getStudentsOfCourse(courseDto.getCourseTitle());
            modelAndView = new ModelAndView("showUserOfCourse");
            modelAndView.addObject("userOfCourse", studentOfCourse);
            return modelAndView;
        } catch (Exception e) {
            new ModelAndView("error", "errorMsg", e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "unconfirmedUser", method = RequestMethod.GET)
    public ModelAndView getUnconfirmedUsers() {
        List<User> waitingUsers = adminService.findByStatus(StatusType.AWAITING);
        List<User> acceptedUsers = adminService.findByStatus(StatusType.ACCEPTED);
        List<User> users = waitingUsers;
        users.addAll(acceptedUsers);
        users.removeIf(user -> user.getRole().equals(UserRole.ADMIN));
        ModelAndView modelAndView = new ModelAndView("confirmUser");
        modelAndView.addObject("waitingUsers", users);
        return modelAndView;
    }

    @RequestMapping(value = "confirmUser", method = RequestMethod.GET)
    public ModelAndView confirmUsers(@ModelAttribute("user") UserDto userDto) {
        userDto.setStatus(StatusType.ACCEPTED);
        userService.updateUser(mapper.convertUserDtoToEntity(userDto));
        ModelAndView modelAndView = new ModelAndView("confirmUser");
        modelAndView.addObject("message", "successfully up to date");
        return modelAndView;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String showSearchPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "searchUser";
    }

    @RequestMapping(value = "searchProcess", method = RequestMethod.POST)
    public ModelAndView searchStudent(@ModelAttribute("user") UserDto userDto) {
        Page<User> userList = userService.findMaxMatch(userDto.getName(), userDto.getFamily(),
                userDto.getEmail(), userDto.getRole(), 0, 100);
        List<UserDto> userDtoPage = mapper.convertEntityListToDto(userList.toList());
        return new ModelAndView("searchUser", "userList", userDtoPage);
    }
}
