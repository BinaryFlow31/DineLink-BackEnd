package com.dinelink.services;

import com.dinelink.entities.LoginRequest;
import com.dinelink.entities.Moderator;
import com.dinelink.repositories.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeratorService {
    private ModeratorRepository moderatorRepository;
    public ModeratorService() {

    }

    @Autowired
    public ModeratorService(ModeratorRepository moderatorRepository) {
        this.moderatorRepository = moderatorRepository;
    }

    public Moderator findByEmail(String email) {
        return moderatorRepository.findByEmail(email);
    }
}
