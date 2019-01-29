package com.kuba.demo.Service;

import com.kuba.demo.Model.Role;
import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.RoleRepository;
import com.kuba.demo.Repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
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
}
