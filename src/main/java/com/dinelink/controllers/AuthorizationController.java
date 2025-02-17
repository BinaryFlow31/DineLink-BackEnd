package com.dinelink.controllers;

import com.dinelink.entities.LoginRequest;
import com.dinelink.entities.LoginResponse;
import com.dinelink.entities.Moderator;
import com.dinelink.repositories.ModeratorRepository;
import com.dinelink.services.ModeratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private ModeratorRepository moderatorRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<Moderator> login(@RequestBody LoginRequest loginRequest) {

        Moderator moderator = moderatorService.login(loginRequest);

        return new ResponseEntity<Moderator>(response, HttpStatus.OK);
    }

}
}
