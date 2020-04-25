package com.example.notes.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Note {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    @ManyToMany
    public List<User> user;

    @OneToMany
    public List<Attachment> attachments;

    @Column
    public String value;

}
