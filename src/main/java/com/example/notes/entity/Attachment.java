package com.example.notes.entity;

import javax.persistence.*;

@Entity
public class Attachment {

    @ManyToOne
    public Note note;

    @Id
    @Column
    public int Id;

    @Column
    public String name;

    @Lob
    public byte[] value;
}
