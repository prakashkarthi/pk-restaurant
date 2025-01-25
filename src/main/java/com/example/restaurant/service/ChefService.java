package com.example.restaurant.service;

import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {

    private final OrderRepository orderRepository;

    @Autowired
    public ChefService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Get orders based on their status.
     *
     * @param statuses List of statuses to filter orders (e.g., "Pending", "In Progress")
     * @return List of orders with the specified statuses
     */
    public List<Order> getOrdersByStatus(List<String> statuses) {
        return orderRepository.findByStatusIn(statuses);
    }

    /**
     * Get order by its ID.
     *
     * @param orderId The ID of the order
     * @return The order if found, or null if not found
     */
    public Order getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    /**
     * Update the status of an order.
     *
     * @param order The order to be updated
     * @return The updated order
     */
    public Order updateOrderStatus(Order order) {
        return orderRepository.save(order);
    }
}
