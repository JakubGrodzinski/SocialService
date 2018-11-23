package com.kuba.demo.Service;

import com.kuba.demo.Model.Role;
import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.RoleRepository;
import com.kuba.demo.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

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
}
