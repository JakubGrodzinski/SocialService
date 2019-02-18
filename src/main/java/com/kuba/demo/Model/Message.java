package com.kuba.demo.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date creationDate;
    @ManyToOne
    private User userCreator;
    @ManyToOne
    private Conversation conversation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
