package com.dinelink.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sub_category_id")
    private Integer subCategoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name= "photo")
    private Blob photo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodItem> foodItems = new ArrayList<>();

    // Derived method to count food items dynamically
    public int getNoOfItems() {
        return foodItems != null ? foodItems.size() : 0;
    }
}