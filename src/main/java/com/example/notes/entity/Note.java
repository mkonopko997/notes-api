package com.example.notes.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Note {
    @Id
    @Column
    public int Id;

    @ManyToMany
    public List<User> user;

    @OneToMany
    public List<Attachment> attachments;

    @Column
    public String value;

}
