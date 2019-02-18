package com.kuba.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conversations")
public class Conversation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;
    @OneToMany
    private Set<Message> messages = new HashSet<>();


}
