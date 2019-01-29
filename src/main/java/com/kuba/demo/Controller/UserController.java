package com.kuba.demo.Controller;

import com.kuba.demo.Model.Post;
import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.UserRepository;
import com.kuba.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userId}")
    public String showProfile(Model model, @PathVariable("userId") Long userId, HttpSession session, Principal principal)
    {
        User user = userRepository.getOne(userId);
        model.addAttribute("chosenUser", user);
        List<Post> posts = user.getPosts();
        Collections.sort(posts, ((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate())));
        model.addAttribute("chosenPosts", posts);


        User databaseCurrentUser = userService.getLoggedDbUser(session, principal);

        model.addAttribute("current", databaseCurrentUser);
        return "user";
    }
}
