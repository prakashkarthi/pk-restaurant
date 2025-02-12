package com.example.restaurant.restaurant_management_system.service;

import com.example.restaurant.restaurant_management_system.model.OrderDetails;
import com.example.restaurant.restaurant_management_system.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    // Create or update OrderDetails
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    // Get OrderDetails by ID
    public Optional<OrderDetails> getOrderDetailsById(Long id) {
        return orderDetailsRepository.findById(id);
    }

    // Get all OrderDetails for an order
    public List<OrderDetails> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailsRepository.findByOrderId(orderId);
    }

    // Delete OrderDetails by ID
    public void deleteOrderDetails(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    // Get all OrderDetails (if needed)
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }
}
