package com.kuba.demo.Repository;

import com.kuba.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);

    @Query("select u from User u where u.username like %?1%")
    List<User> findUsersLike (String uName);
}
