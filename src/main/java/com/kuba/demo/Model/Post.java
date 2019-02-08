package com.kuba.demo.Model;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private Date creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments= new ArrayList<>();
    @ManyToMany(mappedBy = "postsLikedByUser")
    private List<User> usersWhoLike = new ArrayList<>();

    @ManyToOne
    private User userCreator;


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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getUsersWhoLike() {
        return usersWhoLike;
    }

    public void setUsersWhoLike(List<User> usersWhoLike) {
        this.usersWhoLike = usersWhoLike;
    }

    public void addToUsersWhoLike (User user)
    {
        this.usersWhoLike.add(user);
    }

    public void removeFromUsersWhoLike (User user)
    {
        Iterator<User> userIterator = this.usersWhoLike.iterator();
        while (userIterator.hasNext())
        {
            User tmpUser = userIterator.next();
            if(tmpUser.getId().equals(user.getId()))
            {
                userIterator.remove();
            }
        }
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }
}
