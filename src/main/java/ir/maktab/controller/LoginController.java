package ir.maktab.controller;

import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.User;
import ir.maktab.service.UserService;
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

@Controller
public class LoginController {

    private UserService userService;
    private ModelMapper mapper;

    @Autowired
    public LoginController(UserService userService, ModelMapper mapper) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }


    @RequestMapping(value = "loginProcess", method = RequestMethod.GET)
    public ModelAndView loginProcess(@ModelAttribute("user") UserDto userDto,
                                     Model model) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String message = "Access Denied";
        ModelAndView modelAndView;
        try {
            UserRole userRole = userService.authenticationUser(email, password);
            User userByEmail = userService.findUserByEmail(email);
            UserDto user = convertUserToUserDto(userByEmail);
            if ((!userByEmail.getStatus().equals(StatusType.ACCEPTED)) || (userByEmail.equals(null))) {
                modelAndView = new ModelAndView("error");
                modelAndView.addObject("errorMsg ", message);
                return modelAndView;
            }
            if (userByEmail.equals("") || userByEmail.equals(null)) {
                modelAndView = new ModelAndView("error");
                modelAndView.addObject("errorMsg", message);
                return modelAndView;
            }
            String role = userByEmail.getRole().name().toLowerCase();
            modelAndView = new ModelAndView(role + "Dashboard");
            modelAndView.addObject("user", user);
          //  model.addAttribute("user",user);
            return modelAndView;

        } catch (NullPointerException ne) {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("errorMsg", ne.getMessage());
        }
        return modelAndView;
    }

    public UserDto convertUserToUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }

}
