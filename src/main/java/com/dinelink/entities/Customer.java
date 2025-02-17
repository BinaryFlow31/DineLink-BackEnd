package com.dinelink.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone", nullable = false)
    private String phone;

    @Column(name="last_active", nullable = false)
    private Timestamp lastActive;

    @Column(name="occurrence", nullable = false)
    private Integer occurrence;

}
