package com.example.notes.entity;

import javax.persistence.*;

@Entity
public class Note {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Id
    @Column
    private int Id;

    @Column
    private String value;

}
