package com.example.notes.controller;

import com.example.notes.auth.JwtTokenUtil;
import com.example.notes.entity.User;
import com.example.notes.model.FacebookLoginRequest;
import com.example.notes.model.FacebookLoginResponse;
import com.example.notes.model.JwtResponse;
import com.example.notes.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@CrossOrigin
@RequestMapping(path="/auth")
public class JwtAuthenticationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

//    @PostMapping()
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
//        User user = this.userService.loadUserByUsername(authenticationRequest.username);
//        if (authenticationRequest.password.equals(user.password)) {
//            final String token = jwtTokenUtil.generateToken(authenticationRequest.username);
//            return ResponseEntity.ok(new JwtResponse(token));
//        }
//        return null;
//    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody FacebookLoginRequest authenticationRequest) throws IOException {
        URL url = new URL("https://graph.facebook.com/" + authenticationRequest.id + "?fields=id&access_token=" + authenticationRequest.accessToken);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        System.out.println(content.toString());

        Gson gson = new Gson();
        FacebookLoginResponse facebookLoginResponse = gson.fromJson(content.toString(), FacebookLoginResponse.class);

        if(facebookLoginResponse.id == null) {
            return null;
        }

        User user = this.userService.loadUserByUsername(authenticationRequest.email);

        if(user == null) {
            user = new User();
            user.email = authenticationRequest.email;
            userService.create(user);
        }

        final String token = jwtTokenUtil.generateToken(authenticationRequest.email);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}