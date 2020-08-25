package ir.maktab.controller;

import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.User;
import ir.maktab.service.UserService;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }


    @RequestMapping(value = "loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("user") UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String message = "Access Denied";
        ModelAndView modelAndView;
        try {
            UserRole userRole = userService.authenticationUser(email, password);
            User userByEmail = userService.findUserByEmail(email);
            UserDto modelUser = convertToUserDto(userByEmail);
            if ((!userByEmail.getEnabled().equals(StatusType.ACCEPTED)) || (userByEmail.equals(null))) {
                modelAndView = new ModelAndView("error");
                modelAndView.addObject("errorMsg ", message);
                return modelAndView;
            }
            if (userByEmail.equals("") || userByEmail.equals(null)) {
                modelAndView = new ModelAndView("error");
                modelAndView.addObject("errorMsg", message);
                return modelAndView;
            }
            String role = userRole.name().toLowerCase();
            modelAndView = new ModelAndView(role + "Dashboard");
            modelAndView.addObject("modelUser", modelUser);
            return modelAndView;

        } catch (NullPointerException ne){
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("errorMsg", ne.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String showError(String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject(message);
        return "error";
    }

    private UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
