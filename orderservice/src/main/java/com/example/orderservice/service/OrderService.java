package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Place a new order
    public Order placeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now()); // Set the order date
        order.getOrderItems().forEach(item -> item.setOrder(order)); // Set the order reference for all items
        return orderRepository.save(order); // Save both Order and OrderItems due to cascading
    }

    // Get orders by user ID
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Cancel an order by ID
    public boolean cancelOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }

    // Get order details by ID
    public Optional<Order> getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId); // Fetch order with items due to Eager Fetch or Join
    }
}
