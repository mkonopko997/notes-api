package com.example.notes.service;

import com.example.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserService currentUserService;



}
