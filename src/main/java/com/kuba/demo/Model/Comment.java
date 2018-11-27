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

    private int numberOfLikes;
    @ManyToOne
    private Post post;
}
