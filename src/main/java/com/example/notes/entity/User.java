package com.example.notes.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @Column
    public String email;

    @Column
    public String password;

    @OneToMany(mappedBy = "user")
    public Set<Note> notes;
}
