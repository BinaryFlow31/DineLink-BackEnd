package com.dinelink.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubCategory> subCategories = new ArrayList<>();

    @Column(name="photo")
    private Blob photo;

    // Derived field: This method calculates the number of subcategories dynamically
    public int getNoOfSubCategories() {
        return subCategories != null ? subCategories.size() : 0;
    }
}