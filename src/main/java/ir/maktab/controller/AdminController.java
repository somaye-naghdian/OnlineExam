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
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private Mapper mapper =new Mapper();

    @Autowired
    public AdminController(UserService userService, CourseService courseService,
                           AdminService adminService, ClassificationService classificationService) {
        this.userService = userService;
        this.courseService = courseService;
        this.adminService = adminService;
        this.classificationService = classificationService;
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "adminDashboard";
    }

    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView("showAllUser");
        List<User> userList = userService.showAllUser();
        List<UserDto> userDtoList = mapper.convertToUserDtoList(userList);
        modelAndView.addObject("userList", userDtoList);
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
        userService.updateUser(mapper.convertToUserEntity(userDto));
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
        Page<UserDto> userDtoPage = mapper.convertToPageDto(userList);
        return new ModelAndView("searchUser", "userList", userDtoPage.getContent());
    }

    @RequestMapping(value = "getCourseList", method = RequestMethod.GET)
    public ModelAndView getCourseList() {
        ModelAndView modelAndView = new ModelAndView("showAllCourse");
        List<Course> allCourse = courseService.getAllCourse();
        List<CourseDto> courseDtoList = mapper.convertToCourseDtoList(allCourse);
        modelAndView.addObject("allCourse", courseDtoList);
        return modelAndView;
    }




}

