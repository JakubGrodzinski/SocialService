package com.kuba.demo.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User
{
    private final com.kuba.demo.Model.User user;

    public CurrentUser(String username, String password, Collection<?
            extends GrantedAuthority> authorities,
                       com.kuba.demo.Model.User user) {
        super(username, password, authorities); this.user = user;
    }
    public com.kuba.demo.Model.User getUser() {return user;}
}
