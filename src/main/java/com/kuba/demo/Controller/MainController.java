package com.kuba.demo.Controller;

import com.kuba.demo.Model.CurrentUser;
import com.kuba.demo.Model.Post;
import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.PostRepository;
import com.kuba.demo.Repository.UserRepository;
import com.kuba.demo.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("currentUser")
public class MainController
{

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage (Model model, Principal principal)
    {
        Post post = new Post();
        String name = principal.getName();
        User user = userRepository.findByUsername(name);
        model.addAttribute("newPost", post);
        model.addAttribute("currentUser", user);
        return "main";
    }
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String postPost (@ModelAttribute("newPost") Post post, HttpSession session)
    {
        post.setCreationDate(new Date());
        User user = (User)session.getAttribute("currentUser");
        post.setUserCreator(user);
        postRepository.save(post);
        return "main";
    }



    @ModelAttribute ("posts")
    private List<Post> getAllPosts ()
    {
        return postRepository.findAll();
    }
}
