package com.example.restaurant.restaurant_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.restaurant_management_system.model.Order;
import com.example.restaurant.restaurant_management_system.repository.OrderRepository;

import java.util.List;

@Service
public class ChefService {

    @Autowired
    private OrderRepository orderRepository;

    // Get all orders with a specific status
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByOrderStatus(status);
    }

    // Update the status of an order
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    // Get an order by ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
