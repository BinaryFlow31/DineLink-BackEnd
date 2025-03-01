package com.dinelink.services;

import com.dinelink.entities.*;
import com.dinelink.exceptions.EmailOrPasswordIncorrectException;
import com.dinelink.exceptions.ModeratorNotFoundException;
import com.dinelink.repositories.ModeratorRepository;
import org.springframework.beans.BeanUtils;
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

    public ModeratorResponse saveModerator(ModeratorRequest moderatorRequest) throws Exception {
        Moderator m = moderatorRepository.findByEmail(moderatorRequest.getEmail());
        if (m != null) {
            throw new Exception("A Moderator already exists with this email");
        }
        String name = moderatorRequest.getName();
        String email = moderatorRequest.getEmail();
        String password = passwordEncoder.encode(moderatorRequest.getPassword());
        String roleReq = moderatorRequest.getRole();
        Role role;
        if (roleReq.equalsIgnoreCase("chef")) {
            role = Role.CHEF;
        } else if(roleReq.equalsIgnoreCase("admin")){
            role = Role.ADMIN;
        }else{
            throw new Exception("Invalid Moderator Role");
        }
        Moderator moderator = new Moderator(email, password, name, role);

        Moderator dbModerator = moderatorRepository.save(moderator);
        ModeratorResponse moderatorResponse = new ModeratorResponse();
        BeanUtils.copyProperties(dbModerator, moderatorResponse);
        return moderatorResponse;
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
