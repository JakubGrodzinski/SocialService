package com.kuba.demo.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    private User creatorUser;

    @ManyToMany(mappedBy = "commentsLikedByUser")
    private  List<User> usersWhoLikeComment = new ArrayList<>();


    @OneToMany
    private List<Comment> responseComments = new ArrayList<>();


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

    public List<User> getUsersWhoLikeComment()
    {
        return usersWhoLikeComment;
    }

    public void setUsersWhoLikeComment(List<User> usersWhoLikeComment)
    {
        this.usersWhoLikeComment = usersWhoLikeComment;
    }

    public void addToUsersWhoLikeComment (User user)
    {
        this.usersWhoLikeComment.add(user);
    }

    public void removeFromUsersWhoLikeComment (User user)
    {
        Iterator<User> userIterator = this.usersWhoLikeComment.iterator();
        while (userIterator.hasNext())
        {
            User tmpUser = userIterator.next();
            if(tmpUser.getId().equals(user.getId()))
            {
                userIterator.remove();
            }
        }
    }



    public List<Comment> getResponseComments() {
        return responseComments;
    }

    public void setResponseComments(List<Comment> responseComments) {
        this.responseComments = responseComments;
    }

    public void addToResponseComments (Comment comment)
    {
        this.responseComments.add(comment);
    }
}
