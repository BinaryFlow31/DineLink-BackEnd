package com.dinelink.controllers;

import com.dinelink.entities.LoginRequest;
import com.dinelink.entities.LoginResponse;
import com.dinelink.entities.Moderator;
import com.dinelink.exceptions.EmailOrPasswordIncorrectException;
import com.dinelink.exceptions.ModeratorNotFoundException;
import com.dinelink.repositories.ModeratorRepository;
import com.dinelink.services.ModeratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthorizationController {

    @Autowired
    private ModeratorService moderatorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping(value = "/login")
    public ResponseEntity<Moderator> login(@RequestBody LoginRequest loginRequest) {

        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            Authentication authenticate = authManager.authenticate(token); // Throws exception if fails

            // If authentication is successful
            Moderator response = moderatorService.findByEmail(loginRequest.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (AuthenticationException e) {
            throw new EmailOrPasswordIncorrectException("Either the email or the password is incorrect!");
        }



    }

}

