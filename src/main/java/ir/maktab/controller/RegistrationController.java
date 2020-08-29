package ir.maktab.controller;

import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.*;
import ir.maktab.service.*;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
public class RegistrationController {

    private UserService userService;
    private AdminService adminService;
    private StudentService studentService;
    private TeacherService teacherService;
    private VerificationTokenService verificationTokenService;
    private ModelMapper mapper;

    @Autowired
    public RegistrationController(UserService userService, AdminService adminService
            , VerificationTokenService verificationTokenService, ModelMapper mapper
            , StudentService studentService, TeacherService teacherService) {
        this.userService = userService;
        this.mapper = mapper;
        this.verificationTokenService = verificationTokenService;
        this.adminService = adminService;
        this.studentService=studentService;
        this.teacherService=teacherService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = convertToUserEntity(userDto);
        UserRole role = user.getRole();
        user.setStatus(StatusType.INACTIVE);
        switch (role) {
            case ADMIN:
                Admin admin = adminService.save(user);
                adminService.sendMail(admin);
                break;
            case STUDENT:
                Student student = studentService.save(user);
                studentService.sendMail(student);
                break;
            case TEACHER:
                Teacher teacher = teacherService.save(user);
                teacherService.sendMail(teacher);
                break;
        }
        String message = " Verification Email send to " + userDto.getEmail();
        model.addAttribute("message", message);
        return "simpleMessage";
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ModelAndView verify(@RequestParam("token") String token) {
        ModelAndView confirmModel = null;
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        if (verificationToken == null) {
            confirmModel = new ModelAndView("error");
            confirmModel.addObject("errorMsg", "Invalid Token");
            return confirmModel;
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTime().getTime();
        long expireDate = verificationToken.getExpiryDate().getTime();

        if ((expireDate - now <= 0)) {
            confirmModel = new ModelAndView("error");
            confirmModel.addObject("errorMsg", "ExpireToken");
            return confirmModel;
        }

        if (user != null) {
            user.setStatus(StatusType.AWAITING);
            userService.updateUser(StatusType.AWAITING, user.getEmail());
            confirmModel = new ModelAndView("simpleMessage");
            confirmModel.addObject("message", "Your account will be checked by the administrator");
        }
        else {
            if (user.getStatus().equals(StatusType.AWAITING) ||
                    user.getStatus().equals(StatusType.ACCEPTED)) {
                confirmModel = new ModelAndView("error");
                confirmModel.addObject("errorMsg", "Your account has already been verified");
                return confirmModel;
            }

        }
        return confirmModel;
    }

    public User convertToUserEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

}
