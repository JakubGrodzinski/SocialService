package com.kuba.demo.Model;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(unique = true)
    private String username;
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

    public User(String username, String password, Set<Role> roles)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User() {}
}
