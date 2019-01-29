package com.kuba.demo.Service;

import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface UserService
{
    User findByUsername (String username);

    void saveUser (User user);

    User getLoggedDbUser (HttpSession session, Principal principal);
}
