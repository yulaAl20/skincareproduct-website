package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Place a new order
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    // Get orders by user ID
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    // Cancel an order by ID
    @DeleteMapping("/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        if (orderService.cancelOrder(orderId)) {
            return "Order with ID " + orderId + " has been canceled.";
        } else {
            return "Order with ID " + orderId + " not found.";
        }
    }

    // Get order details by order ID
    @GetMapping("/{orderId}")
    public Optional<Order> getOrderDetails(@PathVariable Long orderId) {
        return orderService.getOrderDetails(orderId);
    }
}
