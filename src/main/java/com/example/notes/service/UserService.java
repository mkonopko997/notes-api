package com.example.notes.service;

import com.example.notes.entity.User;
import com.example.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userEntity = userRepository.findById(username);
        return userEntity.orElse(null);
    }

}
