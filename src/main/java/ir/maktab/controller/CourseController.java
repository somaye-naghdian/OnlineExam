package ir.maktab.controller;

import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.AdminService;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
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
    private ModelMapper modelMapper;

    @Autowired
    public CourseController(UserService userService, CourseService courseService,
                            AdminService adminService, ClassificationService classificationService,
                            ModelMapper modelMapper) {
        this.userService = userService;
        this.courseService = courseService;
        this.adminService = adminService;
        this.classificationService = classificationService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "addUserToCourse", method = RequestMethod.GET)
    public ModelAndView addUserToCourse(Model model) {
        model.addAttribute("user", new UserDto());
        ModelAndView modelAndView = new ModelAndView("course_user");
        List<Course> allCourse = courseService.getAllCourse();
        List<CourseDto> courseDtoList = allCourse.stream()
                .map(this::convertToCourseDto)
                .collect(Collectors.toList());
        modelAndView.addObject("allCourse", courseDtoList);
        return modelAndView;
    }

//   @RequestMapping(value = "addUserToCourseProcess", method = RequestMethod.POST)
//    public ModelAndView addUserToCourseProcess(@ModelAttribute("user") UserDto userDto,
//                                               @RequestParam("course") String courseTitle) {
//        String email = userDto.getEmail();
//        User user = userService.findUserByEmail(email);
//        Course course = courseService.findCourseByTitle(courseTitle);
//      //  users.add(user);
//     //   courseService.updateCourseUsers(users,course.getCourseTitle());
//        ModelAndView modelAndView = new ModelAndView("course_user", "message", "user"+user.getName()+"successfully added to" + course.getCourseTitle() );
//        return modelAndView;
//    }

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

    private CourseDto convertToCourseDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

}
