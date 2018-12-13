package com.kuba.demo.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private Date creationDate;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> usersWhoLike = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    private User creatorUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<User> getUsersWhoLike() {
        return usersWhoLike;
    }

    public void setUsersWhoLike(List<User> usersWhoLike) {
        this.usersWhoLike = usersWhoLike;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getCreatorUser()
    {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser)
    {
        this.creatorUser = creatorUser;
    }
}
