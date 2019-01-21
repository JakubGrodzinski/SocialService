package com.kuba.demo.Controller;

import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/{userId}")
    public String showProfile(Model model, @PathVariable("userId") Long userId, HttpSession session, Principal principal)
    {
        User user = userRepository.getOne(userId);
        model.addAttribute("chosenUser", user);


        User currentUser = (User)session.getAttribute("currentUser");
        if(currentUser == null)
        {
            currentUser = userRepository.findByUsername(principal.getName());
        }
        User databaseCurrentUser = userRepository.getOne(currentUser.getId());

        model.addAttribute("current", databaseCurrentUser);
        return "user";
    }
}
