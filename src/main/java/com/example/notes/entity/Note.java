package com.example.notes.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Note {

    @ManyToMany
    public List<User> user;

    @Id
    @Column
    public int Id;

    @Column
    public String value;

}
