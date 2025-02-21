package com.dinelink.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="feedback_id")
    private Integer feedbackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @Column(name="suggestion", columnDefinition = "TEXT")
    private String suggestion;

    @Column(name="service_rating", nullable = false)
    private Integer serviceRating;

    @Column(name="food_rating", nullable = false)
    private Integer foodRating;

    @Column(name="cleanliness_rating", nullable = false)
    private Integer cleanlinessRating;

    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
