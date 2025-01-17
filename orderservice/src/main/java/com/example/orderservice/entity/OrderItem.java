package com.example.orderservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")  // Maps to the 'order_items' table
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")  // Maps to 'order_items_id' column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // Maps to 'order_id' column and references the 'orders' table
    private Order order;

    @Column(name = "product_id", nullable = false)  // Maps to 'product_id' column
    private Long productId;

    @Column(name = "quantity", nullable = false)  // Maps to 'quantity' column
    private Integer quantity;

    @Column(name = "price", nullable = false)  // Maps to 'price' column
    private Double price;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  // Maps to 'created_at' column
    private LocalDateTime createdAt;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

