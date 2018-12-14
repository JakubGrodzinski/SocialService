package com.kuba.demo.Controller;

import com.kuba.demo.Model.Comment;
import com.kuba.demo.Model.CurrentUser;
import com.kuba.demo.Model.Post;
import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.CommentRepository;
import com.kuba.demo.Repository.PostRepository;
import com.kuba.demo.Repository.UserRepository;
import com.kuba.demo.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    CommentRepository commentRepository;


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
    public String postPost (@ModelAttribute("newPost") Post post, HttpSession session)
    {
        post.setCreationDate(new Date());
        User user = (User)session.getAttribute("currentUser");
        User notDetached = userRepository.findByUsername(user.getUsername());
        post.setUserCreator(notDetached);
        postRepository.save(post);
        return "redirect:/main";
    }

    @RequestMapping(value = "/mai", method = RequestMethod.GET)
    public String redirectFromMai ()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/mai/{postId}", method = RequestMethod.POST)
    public String postComment (@ModelAttribute("newComment") Comment comment, @PathVariable("postId") Long postId, HttpSession session)
    {
        comment.setCreationDate(new Date());
        User user = (User)session.getAttribute("currentUser");
        User notDetached = userRepository.findByUsername(user.getUsername());
        comment.setCreatorUser(notDetached);
        comment.setPost(postRepository.getOne(postId));
        commentRepository.save(comment);
        return "redirect:/main";
    }



    @ModelAttribute("posts")
    public List<Post> getAllPosts ()
    {
        return postRepository.getOrderedPosts();
    }

    @ModelAttribute("comments")
    public List<Comment> getAllComments ()
    {
        return commentRepository.getOrderedComments();
    }

}
