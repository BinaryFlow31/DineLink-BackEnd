package com.dinelink.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="moderators")
public class Moderator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="moderator_id")
    private Integer moderatorId;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Column(name = "name", nullable = false)
    private String name;
}
