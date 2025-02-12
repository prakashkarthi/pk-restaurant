package com.example.restaurant.restaurant_management_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restaurant.restaurant_management_system.model.Order;
import com.example.restaurant.restaurant_management_system.service.ChefService;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")
public class ChefController {

    @Autowired
    private ChefService chefService;

    // Get all pending orders
    @GetMapping("/orders/pending")
    public ResponseEntity<List<Order>> getPendingOrders() {
        List<Order> pendingOrders = chefService.getOrdersByStatus("Pending");
        return ResponseEntity.ok(pendingOrders);
    }

    // Update order status
    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestBody String status) {
        Order updatedOrder = chefService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // Get an order by ID
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = chefService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
