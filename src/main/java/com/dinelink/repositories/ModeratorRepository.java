package com.dinelink.repositories;

import com.dinelink.entities.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator,Integer> {
    public Moderator findByEmail(String email);
    public List <Moderator> findAllByRole(String role);
}

