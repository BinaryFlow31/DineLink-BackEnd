package com.dinelink.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Blob;

@Entity
@Data
@Table(name="food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Integer itemId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name="photo")
    private Blob photo;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="sub_category_id" , referencedColumnName = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @Override
    public String toString() {
        return "FoodItem{id=" + itemId + ", name='" + name + "', price=" + price + "}";
    }
}
