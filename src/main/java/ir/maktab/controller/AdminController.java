package ir.maktab.controller;

import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.User;
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


    private Mapper mapper;

    @Autowired
    public AdminController(UserService userService, Mapper mapper) {
        this.userService = userService;
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





}

