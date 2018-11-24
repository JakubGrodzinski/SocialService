package com.kuba.demo.Controller;

import com.kuba.demo.Model.User;
import com.kuba.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController
{
    @Autowired
    UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration (Model model)
    {
        User user = new User();
        model.addAttribute("newUser", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser (@ModelAttribute User user)
    {
        userService.saveUser(user);
        return "login";
    }
}
