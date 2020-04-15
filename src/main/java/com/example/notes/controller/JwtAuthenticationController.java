package com.example.notes.controller;

import com.example.notes.auth.JwtTokenUtil;
import com.example.notes.entity.User;
import com.example.notes.model.JwtRequest;
import com.example.notes.model.JwtResponse;
import com.example.notes.service.CurrentUserService;
import com.example.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(path="/auth")
public class JwtAuthenticationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUserService currentUserService;

    @PostMapping(path="/try")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        User user = this.userService.loadUserByUsername(authenticationRequest.username);
        if (authenticationRequest.password.equals(user.password)) {
            this.currentUserService.user = user;
            final String token = jwtTokenUtil.generateToken(authenticationRequest.username);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return null;
    }

}