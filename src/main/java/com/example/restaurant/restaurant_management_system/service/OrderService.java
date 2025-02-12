package com.example.restaurant.restaurant_management_system.service;

import com.example.restaurant.restaurant_management_system.model.Order;
import com.example.restaurant.restaurant_management_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Fetch all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Other methods for creating, updating, or deleting orders can be added here
}
