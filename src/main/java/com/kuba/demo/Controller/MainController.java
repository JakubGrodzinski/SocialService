package com.kuba.demo.Controller;

import com.kuba.demo.Model.*;
import com.kuba.demo.Repository.*;
import com.kuba.demo.Service.MyUserDetailsService;
import com.kuba.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
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
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    MessageRepository messageRepository;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage (Model model, Principal principal)
    {
        Post post = new Post();
        Comment comment = new Comment();
        Message message = new Message();
        String name = principal.getName();
        User user = userRepository.findByUsername(name);
        model.addAttribute("newPost", post);
        model.addAttribute("currentUser", user);
        model.addAttribute("newComment", comment);
        model.addAttribute("newMessage", message);
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

    @RequestMapping(value = "/unlike/**", method = RequestMethod.GET)
    public String redirectFromUnlike ()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/unlike/{postId}", method = RequestMethod.POST)
    public String unlikeAPost (@PathVariable("postId") Long postId, HttpSession session, Principal principal)
    {
        Post post = postRepository.getOne(postId);
        User user = userService.getLoggedDbUser(session, principal);
        post.removeFromUsersWhoLike(user);
        user.removeFromPostsLikedByUser(post);
        postRepository.save(post);
        userRepository.save(user);
        return "redirect:/main";
    }

    @RequestMapping(value = "/likeAComment/**", method = RequestMethod.GET)
    public String redirectFromLikeAComment ()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/likeAComment/{commentId}", method = RequestMethod.POST)
    public String likeAComment (@PathVariable("commentId") Long commentId, HttpSession session, Principal principal)
    {
        Comment comment = commentRepository.getOne(commentId);
        User user = userService.getLoggedDbUser(session, principal);
        comment.addToUsersWhoLikeComment(user);
        user.addToCommentsLikedByUser(comment);
        commentRepository.save(comment);
        userRepository.save(user);
        return "redirect:/main";
    }

    @RequestMapping(value = "/unlikeAComment/**", method = RequestMethod.GET)
    public String redirectFromUnlikeAComment ()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/unlikeAComment/{commentId}", method = RequestMethod.POST)
    public String unlikeAComment (@PathVariable("commentId") Long commentId, HttpSession session, Principal principal)
    {
        Comment comment = commentRepository.getOne(commentId);
        User user = userService.getLoggedDbUser(session, principal);
        comment.removeFromUsersWhoLikeComment(user);
        user.removeFromCommentsLikedByUser(comment);
        commentRepository.save(comment);
        userRepository.save(user);
        return "redirect:/main";
    }

    @RequestMapping(value = "/sendAMessage/**", method = RequestMethod.GET)
    public String redirectFromSendAMessage ()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/sendAMessage/{friendId}/{conversationId}", method = RequestMethod.POST)
    public String sendAMessage (@ModelAttribute("newMessage") Message message, @PathVariable("friendId") Long friendId, @PathVariable("conversationId") Long conversationId, HttpSession session, Principal principal)
    {
        User friend = userRepository.getOne(friendId);
        User user = userService.getLoggedDbUser(session, principal);
        Conversation conversation = conversationRepository.getOne(conversationId);
        message.setCreationDate(new Date());
        message.setConversation(conversation);
        message.setUserCreator(user);
        messageRepository.save(message);
        return "redirect:/main";
    }

    @ModelAttribute("posts")
    public List<Post> getAllPosts ()
    {
        List<Post> posts = postRepository.getOrderedPosts();
        return posts;
    }

    @ModelAttribute("conversations")
    public List<Conversation> getConversationsOfTheCurrentUser (HttpSession session, Principal principal)
    {
        User user = userService.getLoggedDbUser(session, principal);
        return conversationRepository.getIfInvolvesUser(user);
    }


}
