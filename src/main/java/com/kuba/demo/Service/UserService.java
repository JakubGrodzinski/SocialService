package com.kuba.demo.Service;

import com.kuba.demo.Model.User;

public interface UserService
{
    User findByUsername (String username);

    void saveUser (User user);
}
