package com.dinelink.services;

import com.dinelink.entities.LoginRequest;
import com.dinelink.entities.Moderator;
import com.dinelink.exceptions.EmailOrPasswordIncorrectException;
import com.dinelink.exceptions.ModeratorNotFoundException;
import com.dinelink.repositories.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModeratorService {

    private BCryptPasswordEncoder passwordEncoder;
    private ModeratorRepository moderatorRepository;

    @Autowired
    public ModeratorService(BCryptPasswordEncoder passwordEncoder, ModeratorRepository moderatorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.moderatorRepository = moderatorRepository;
    }


    public Moderator login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Moderator moderator = moderatorRepository.findByEmail(email);
        if (moderator != null) {
            boolean flag = passwordEncoder.matches(password, moderator.getPassword());
            if (flag) {
                return moderator;
            } else {
                throw new EmailOrPasswordIncorrectException("Either Email or Password is incorrect");
            }
        }
        throw new ModeratorNotFoundException("Moderator does not exist");

    }
}
