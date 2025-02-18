package com.dinelink.services;

import com.dinelink.entities.Moderator;
import com.dinelink.exceptions.ModeratorNotFoundException;
import com.dinelink.repositories.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ModeratorDetailsService implements UserDetailsService {

    private ModeratorRepository repository;

    public  ModeratorDetailsService() {

    }

    @Autowired
    public ModeratorDetailsService(ModeratorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Moderator moderator = repository.findByEmail(email);

        if(moderator == null) {
            throw new ModeratorNotFoundException("Moderator not found at " + email);
        }

        return User.builder()
                .username(moderator.getEmail())
                .password(moderator.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + moderator.getRole().name())))
                .build();
    }
}
