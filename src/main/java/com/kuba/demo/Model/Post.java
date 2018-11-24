package com.kuba.demo.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> usersWhoLike = new ArrayList<>();

    private int numberOfLikes;
}
