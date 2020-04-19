package com.example.notes.controller;

import com.example.notes.entity.User;
import com.example.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping()
    public @ResponseBody
    String addNewUser(
            @RequestBody User u
    ) {
        userRepository.save(u);
        return "Saved";
    }

    @GetMapping()
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


}