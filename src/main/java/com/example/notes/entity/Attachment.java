package com.example.notes.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class Attachment {

    @ManyToOne
    public Note note;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    @Column
    public String name;

    @Column
    public String type;

    @Lob
    public byte[] value;
}
