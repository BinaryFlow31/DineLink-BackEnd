package com.dinelink.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Integer orderItemId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "food_item_id", referencedColumnName = "item_id", nullable = false)
    private FoodItem foodItem;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    @Column(name="subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
}