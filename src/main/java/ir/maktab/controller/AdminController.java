package ir.maktab.controller;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.dto.ClassificationDto;
import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.AdminService;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private UserService userService;
    private CourseService courseService;
    private AdminService adminService;
    private ClassificationService classificationService;
    private ModelMapper modelMapper;

    @Autowired
    public AdminController(UserService userService, CourseService courseService,
                           AdminService adminService, ClassificationService classificationService,
                           ModelMapper modelMapper) {
        this.userService = userService;
        this.courseService = courseService;
        this.adminService = adminService;
        this.classificationService = classificationService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "adminDashboard";
    }

    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView("showAllUser");
        List<User> userList = userService.showAllUser();
        List<UserDto> userDtoList = convertToUserDtoList(userList);
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
        userDto.setEnabled(StatusType.ACCEPTED);
        userService.updateUser(convertToUserEntity(userDto));
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
        Page<UserDto> userDtoPage = convertToPageDto(userList);
        return new ModelAndView("searchUser", "userList", userDtoPage.getContent());
    }

    @RequestMapping(value = "getCourseList", method = RequestMethod.GET)
    public ModelAndView getCourseList() {
        ModelAndView modelAndView = new ModelAndView("showAllCourse");
        List<Course> allCourse = courseService.getAllCourse();
        List<CourseDto> courseDtoList = allCourse.stream()
                .map(this::convertToCourseDto)
                .collect(Collectors.toList());
        modelAndView.addObject("allCourse", courseDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "addClassification", method = RequestMethod.GET)
    public String addClassification(Model model) {
        model.addAttribute("classification", new ClassificationDto());
        return "addClassification";
    }

    @RequestMapping(value = "addClassificationProcess", method = RequestMethod.GET)
    public ModelAndView addClassificationProcess(@ModelAttribute("classification") ClassificationDto classificationDto) {
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        try {
            classificationService.addClassification(ConvertToClassificationEntity(classificationDto));

        } catch (CourseAlreadyExist e) {
            new ModelAndView("error", "errorMsg", e.getMessage());
        }
        String message = " classification " + classificationDto.getClassificationTitle() + " successfully add";
        new ModelAndView("simpleMessage", "message", message);

        return modelAndView;
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
        //  Classification classificationByTitle = classificationService.getClassificationByTitle(title);
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


    private CourseDto convertToCourseDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

    private Classification ConvertToClassificationEntity(ClassificationDto classificationDto) {
        return modelMapper.map(classificationDto, Classification.class);
    }

    private UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private List<UserDto> convertToUserDtoList(List<User> users) {
        List<UserDto> usersDto = users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
        return usersDto;
    }

    private User convertToUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


    public Page<UserDto> convertToPageDto(Page<User> objects) {
        Page<UserDto> userDtoPage = objects.map(this::convertToUserDto);
        return userDtoPage;
    }

}

