package com.example.restaurant.restaurant_management_system.repository;

import com.example.restaurant.restaurant_management_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // You can add custom queries here if needed
}
