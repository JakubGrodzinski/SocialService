package com.kuba.demo.Model;

import com.kuba.demo.Validator.NoSuchNameInDb;
import com.kuba.demo.Validator.ValidPassword;
import com.kuba.demo.ValidatorGroup.RegistrationValidatorGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @NoSuchNameInDb(groups = RegistrationValidatorGroup.class)
    private String username;
    @ValidPassword(groups = RegistrationValidatorGroup.class)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    //Posty użytkownika
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCreator")
    private List<Post> posts = new ArrayList<>();
    //Znajomi użytkownika
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> friends = new ArrayList<>();
    //Komentarze użytkownika
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    //Ci, których chce poznać użytkownik
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> wantedByUser = new ArrayList<>();
    //Ci, którzy chcą poznać użytkownika
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> userIsWanted = new ArrayList<>();
    //Posty, które lubi użytkownik
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Post> postsLikedByUser = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addToFriends (User user)
    {
        this.friends.add(user);
    }

    public void removeFromFriends (User user)
    {
        Iterator<User> userIterator = this.friends.iterator();
        while (userIterator.hasNext())
        {
            User tmpUser = userIterator.next();
            if(tmpUser.getId().equals(user.getId()))
            {
                userIterator.remove();
            }
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getWantedByUser() {
        return wantedByUser;
    }

    public void setWantedByUser(List<User> wantedByUser) {
        this.wantedByUser = wantedByUser;
    }

    public List<User> getUserIsWanted() {
        return userIsWanted;
    }

    public void setUserIsWanted(List<User> userIsWanted) {
        this.userIsWanted = userIsWanted;
    }

    public void addToWantedByUser (User user)
    {
        this.wantedByUser.add(user);
    }
    public void removeFromWantedByUser (User user)
    {
        Iterator<User> userIterator = this.wantedByUser.iterator();
        while (userIterator.hasNext())
        {
            User tmpUser = userIterator.next();
            if(tmpUser.getId().equals(user.getId()))
            {
                userIterator.remove();
            }
        }
    }
    public void addToUserIsWanted (User user)
    {
        this.userIsWanted.add(user);
    }
    public void removerFromUserIsWanted (User user)
    {
        Iterator<User> userIterator = this.userIsWanted.iterator();
        while (userIterator.hasNext())
        {
            User tmpUser = userIterator.next();
            if(tmpUser.getId().equals(user.getId()))
            {
                userIterator.remove();
            }
        }
    }

    public User(String username, String password, Set<Role> roles)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User() {}

    public List<Post> getPostsLikedByUser()
    {
        return postsLikedByUser;
    }

    public void setPostsLikedByUser(List<Post> postsLikedByUser)
    {
        this.postsLikedByUser = postsLikedByUser;
    }

    public void addToPostsLikedByUser (Post post)
    {
        this.postsLikedByUser.add(post);
    }

    public void removeFromPostsLikedByUser (Post post)
    {
        Iterator<Post> postIterator = this.postsLikedByUser.iterator();
        while (postIterator.hasNext())
        {
            Post tmpPost = postIterator.next();
            if(tmpPost.getId().equals(post.getId()))
            {
                postIterator.remove();
            }
        }
    }
}
