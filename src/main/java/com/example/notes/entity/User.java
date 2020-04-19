package com.example.notes.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @Column
    public String email;

    @Column
    public String password;

    @ManyToMany
    public List<Note> notes;
}
