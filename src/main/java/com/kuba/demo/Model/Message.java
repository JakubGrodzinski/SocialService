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
}
