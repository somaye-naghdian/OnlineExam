package ir.maktab.controller;

import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import ir.maktab.util.Mapper;
import ir.maktab.util.StatusType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    private UserService userService;
    private CourseService courseService;
    private Mapper mapper;

    @Autowired
    public LoginController(UserService userService, Mapper mapper,
                           CourseService courseService) {
        this.mapper = mapper;
        this.userService = userService;
        this.courseService =courseService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView("login");
        String message = (String) model.getAttribute("message");
        modelAndView.addObject("user", new UserDto());
        modelAndView.addObject("message",model.getAttribute("message"));
        return modelAndView;
    }


    @RequestMapping(value = "loginProcess", method = RequestMethod.GET)
    public ModelAndView loginProcess(@ModelAttribute("user") UserDto userDto, Model model,
                                     HttpServletRequest request) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        try {
            if (userService.authenticationUser(email, password) == null) {
                modelAndView = new ModelAndView("simpleMessage");
                modelAndView.addObject("message ", "password or email is wrong");
                return modelAndView;
            }
            User userByEmail = userService.findUserByEmail(email);
            UserDto userDto1 = mapper.convertUserEntityToDto(userByEmail);
            if ((!userByEmail.getStatus().equals(StatusType.ACCEPTED)) || (userByEmail.equals(null))) {
                modelAndView = new ModelAndView("simpleMessage");
                modelAndView.addObject("message ", "Access Denied");
                return modelAndView;
            }
            if (userByEmail.equals("") || userByEmail.equals(null)) {
                modelAndView = new ModelAndView("simpleMessage");
                modelAndView.addObject("message", "Access Denied");
                return modelAndView;
            }
            String role = userByEmail.getRole().name().toLowerCase();
            modelAndView = new ModelAndView(role + "Dashboard");
            List<Course> courseList = userByEmail.getCourseList();
            modelAndView.addObject("user", userDto1);
            modelAndView.addObject("courseList", courseList);
            session.setAttribute("user",userDto1);
            return modelAndView;

        } catch (NullPointerException ne) {
            modelAndView = new ModelAndView("simpleMessage");
            modelAndView.addObject("message", ne.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(value = "/loginError")
    public  ModelAndView showLoginError(Model model){
        model.addAttribute("message","Invalid Email or Password");
        return login(model);
    }

}
