package com.dinelink.controllers;

import com.dinelink.entities.LoginRequest;
import com.dinelink.entities.Moderator;
import com.dinelink.services.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthorizationController {

    private ModeratorService moderatorService;

    @Autowired
    public AuthorizationController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Moderator> login(@RequestBody LoginRequest loginRequest) {
        Moderator moderator = moderatorService.login(loginRequest);

        return new ResponseEntity<Moderator>(moderator, HttpStatus.OK);
    }
}

