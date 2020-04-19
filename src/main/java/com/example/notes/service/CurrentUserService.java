package com.example.notes.service;

import com.example.notes.auth.JwtTokenUtil;
import com.example.notes.entity.User;
import com.example.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User user() {
        String username = this.jwtTokenUtil.getUsernameFromToken(this.jwtTokenUtil.token);
        return this.userRepository.findById(username).get();
    }

}
