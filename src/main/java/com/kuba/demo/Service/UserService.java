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

    void sendInvitationService (User userWanting, User userWanted);

    void acceptInvitationService (User userDeciding, User userWanting);

    void declineInvitationService (User userDeciding, User userWanting);

    void removeFromFriendsService (User userDeciding, User userBeingRemoved);
}
