package com.example.notes.model;
import java.io.Serializable;

public class FacebookLoginRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    public String id;
    public String name;
    public String email;
    public String accessToken;
}
