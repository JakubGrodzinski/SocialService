package com.kuba.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    private String name;
}
