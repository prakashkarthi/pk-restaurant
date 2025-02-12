package com.example.restaurant.restaurant_management_system.repository;

import com.example.restaurant.restaurant_management_system.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    // Custom query to find order details by order ID
    List<OrderDetails> findByOrderId(Long orderId);
}
