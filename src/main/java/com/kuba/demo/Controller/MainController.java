package com.kuba.demo.Controller;

import com.kuba.demo.Model.Comment;
import com.kuba.demo.Model.CurrentUser;
import com.kuba.demo.Model.Post;
import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.CommentRepository;
import com.kuba.demo.Repository.PostRepository;
import com.kuba.demo.Repository.UserRepository;
import com.kuba.demo.Service.MyUserDetailsService;
import com.kuba.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage (Model model, Principal principal)
    {
        Post post = new Post();
        Comment comment = new Comment();
        String name = principal.getName();
        User user = userRepository.findByUsername(name);
        model.addAttribute("newPost", post);
        model.addAttribute("currentUser", user);
        model.addAttribute("newComment", comment);
        return "main";
    }
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String postPost (@ModelAttribute("newPost") Post post, HttpSession session, Principal principal)
    {
        post.setCreationDate(new Date());
        post.setUserCreator(userService.getLoggedDbUser(session, principal));
        postRepository.save(post);
        return "redirect:/main";
    }

    @RequestMapping(value = "/mai/**", method = RequestMethod.GET)
    public String redirectFromMai ()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/mai/{postId}", method = RequestMethod.POST)
    public String postComment (@ModelAttribute("newComment") Comment comment, @PathVariable("postId") Long postId, HttpSession session, Principal principal)
    {
        comment.setCreationDate(new Date());
        comment.setCreatorUser(userService.getLoggedDbUser(session, principal));
        comment.setPost(postRepository.getOne(postId));
        commentRepository.save(comment);
        return "redirect:/main";
    }

    @RequestMapping(value = "/like/**", method = RequestMethod.GET)
    public String redirectFromLike ()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/like/{postId}", method = RequestMethod.POST)
    public String likeAPost(@PathVariable("postId") Long postId, HttpSession session, Principal principal)
    {
        Post post = postRepository.getOne(postId);
        User user = userService.getLoggedDbUser(session, principal);
        post.addToUsersWhoLike(user);
        user.addToPostsLikedByUser(post);
        postRepository.save(post);
        userRepository.save(user);
        return "redirect:/main";
    }



    @ModelAttribute("posts")
    public List<Post> getAllPosts ()
    {
        List<Post> posts = postRepository.getOrderedPosts();
        return posts;
    }


}
