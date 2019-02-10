package com.kuba.demo.Service;

import com.kuba.demo.Model.Role;
import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.RoleRepository;
import com.kuba.demo.Repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Primary
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User getLoggedDbUser(HttpSession session, Principal principal)
    {
        User currentUser = (User)session.getAttribute("currentUser");
        if(currentUser == null)
        {
            currentUser = userRepository.findByUsername(principal.getName());
        }
        return userRepository.getOne(currentUser.getId());
    }

    @Override
    public void sendInvitationService(User userWanting, User userWanted)
    {
        userWanting.addToWantedByUser(userWanted);
        userWanted.addToUserIsWanted(userWanting);
        userRepository.save(userWanting);
        userRepository.save(userWanted);
    }

    @Override
    public void acceptInvitationService(User userDeciding, User userWanting)
    {
        userDeciding.addToFriends(userWanting);
        userDeciding.removeFromUserIsWanted(userWanting);
        userWanting.addToFriends(userDeciding);
        userWanting.removeFromWantedByUser(userDeciding);
        userRepository.save(userDeciding);
        userRepository.save(userWanting);
    }

    @Override
    public void declineInvitationService(User userDeciding, User userWanting)
    {
        userDeciding.removeFromUserIsWanted(userWanting);
        userWanting.removeFromWantedByUser(userDeciding);
        userRepository.save(userDeciding);
        userRepository.save(userWanting);
    }

    @Override
    public void removeFromFriendsService(User userDeciding, User userBeingRemoved)
    {
        userDeciding.removeFromFriends(userBeingRemoved);
        userBeingRemoved.removeFromFriends(userDeciding);
        userRepository.save(userDeciding);
        userRepository.save(userBeingRemoved);
    }

    @Scheduled(cron = "0 * * ? * *")
    public void suggestFriends ()
    {

        List<User> allUsers = userRepository.findAll();
        for (User user: allUsers)
        {
            Set<User> userSet = new HashSet<>();
            for(User user1: user.getFriends())
            {
                for(User user2: user1.getFriends())
                {
                    if((!user2.equals(user)) && (!user.getFriends().contains(user2)))
                    {
                        userSet.add(user2);
                    }
                }
            }
            user.setSuggestedFriends(userSet);
            userRepository.save(user);
        }
    }
}
