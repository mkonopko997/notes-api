package com.example.notes.controller;


import java.util.Collection;
import java.util.Objects;

import com.example.notes.auth.config.JwtTokenUtil;
import com.example.notes.model.JwtRequest;
import com.example.notes.model.JwtResponse;
import com.example.notes.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(path="/auth")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(path="/try")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.username);

//        authenticate(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        final String token = jwtTokenUtil.generateToken(authenticationRequest.username);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password, Collection<? extends GrantedAuthority> authorities) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, authorities));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}