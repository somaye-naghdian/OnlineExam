package ir.maktab.controller;

import ir.maktab.exceptions.UserAlreadyExistsException;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.User;
import ir.maktab.model.entity.VerificationToken;
import ir.maktab.service.MailService;
import ir.maktab.service.TokenService;
import ir.maktab.service.UserService;
import ir.maktab.util.StatusType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
public class RegistrationController {

    private UserService userService;
    private TokenService tokenService;
    private MailService mailService;
    private ModelMapper modelMapper;

    @Autowired
    public RegistrationController(UserService userService, TokenService tokenService,
                                  MailService mailService,ModelMapper modelMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.mailService = mailService;
        this.modelMapper=modelMapper;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") UserDto userDto
            , Model model) {
        try {
            User user = convertToUserEntity(userDto);
            user.setEnabled(StatusType.INACTIVE);
            userService.registerNewUser(user);
            sendTo(user);

        } catch (UserAlreadyExistsException e) {
            new ModelAndView("error", "errorMsg", e.getMessage());
        }
        String message = " Verification Email send to " + userDto.getEmail();
        model.addAttribute("message", message);
        return "simpleMessage";
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ModelAndView verify(@RequestParam("token") String token) {
        ModelAndView confirmModel = null;
        VerificationToken verificationToken = tokenService.findByToken(token);

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
        if (user.getEnabled().equals(StatusType.AWAITING) ||
                user.getEnabled().equals(StatusType.ACCEPTED)) {
            confirmModel = new ModelAndView("error");
            confirmModel.addObject("errorMsg", "Your account has already been verified");
            return confirmModel;
        }

        if (user != null) {
            user.setEnabled(StatusType.AWAITING);
            userService.updateUser(StatusType.AWAITING, user.getEmail());
            confirmModel = new ModelAndView("simpleMessage");
            confirmModel.addObject("message", "Your Account Verified");
        }
        return confirmModel;
    }

    private boolean sendTo(User user) {

        VerificationToken verificationToken = new VerificationToken(user);
        tokenService.save(verificationToken);
        String mailText = "To confirm your account, please click here : "
                + "http://localhost:8080/verify?token=" + verificationToken.getToken();
        return mailService.sendMail(user.getEmail(), mailText, "Account Verification");
    }

    private User convertToUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
