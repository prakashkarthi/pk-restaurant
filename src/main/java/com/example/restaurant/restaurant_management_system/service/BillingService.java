package com.example.restaurant.restaurant_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.restaurant_management_system.dto.OrderDetailsDTO;
import com.example.restaurant.restaurant_management_system.model.OrderDetails;
import com.example.restaurant.restaurant_management_system.repository.OrderDetailsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsDTO convertToOrderDetailsDTO(OrderDetails orderDetails) {
        OrderDetailsDTO dto = new OrderDetailsDTO();
        dto.setId(orderDetails.getId());
        dto.setMenuId(orderDetails.getMenuItem().getId());
        dto.setItemName(orderDetails.getMenuItem().getItemName());
        dto.setQuantity(orderDetails.getQuantity());
        dto.setPrice(orderDetails.getPrice());
        dto.setTotalPrice(orderDetails.getPrice() * orderDetails.getQuantity());
        return dto;
    }

    public OrderDetails convertToOrderDetailsEntity(OrderDetailsDTO dto) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(dto.getId());
        // Assuming Menu retrieval is handled elsewhere
        // Example: orderDetails.setMenuItem(menuService.getMenuById(dto.getMenuId()));
        orderDetails.setQuantity(dto.getQuantity());
        orderDetails.setPrice(dto.getPrice());
        return orderDetails;
    }

    // Calculate the total bill for an order
    public double calculateBill(Long orderId) {
        List<OrderDetails> orderDetails = orderDetailsRepository.findByOrderId(orderId);

        // Calculate total cost
        return orderDetails.stream()
                .mapToDouble(details -> details.getQuantity() * details.getPrice())
                .sum();
    }

    // Get the order details for billing
    public List<OrderDetailsDTO> getOrderDetails(Long orderId) {
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(orderId);
        return orderDetailsList.stream()
                .map(this::convertToOrderDetailsDTO)
                .collect(Collectors.toList());
    }
}
