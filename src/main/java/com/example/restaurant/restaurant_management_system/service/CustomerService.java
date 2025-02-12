package com.example.restaurant.restaurant_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.restaurant_management_system.dto.OrderDTO;
import com.example.restaurant.restaurant_management_system.dto.OrderDetailsDTO;
import com.example.restaurant.restaurant_management_system.model.Menu;
import com.example.restaurant.restaurant_management_system.model.Order;
import com.example.restaurant.restaurant_management_system.model.OrderDetails;
import com.example.restaurant.restaurant_management_system.repository.MenuRepository;
import com.example.restaurant.restaurant_management_system.repository.OrderDetailsRepository;
import com.example.restaurant.restaurant_management_system.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    // Convert Order entity to OrderDTO
    public OrderDTO convertToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());

        // Convert list of OrderDetails to OrderDetailsDTO
        List<OrderDetailsDTO> orderDetailsDTOList = order.getOrderDetails().stream()
            .map(orderDetails -> {
                OrderDetailsDTO dto = new OrderDetailsDTO();
                dto.setId(orderDetails.getId());
                dto.setMenuId(orderDetails.getMenuItem().getId());
                dto.setItemName(orderDetails.getMenuItem().getItemName());
                dto.setQuantity(orderDetails.getQuantity());
                dto.setPrice(orderDetails.getPrice());
                dto.setTotalPrice(orderDetails.getPrice() * orderDetails.getQuantity());
                return dto;
            })
            .collect(Collectors.toList());

        orderDTO.setOrderDetails(orderDetailsDTOList);
        return orderDTO;
    }

    // Get all menu items
    public List<Menu> getMenuItems() {
        return menuRepository.findAll();
    }

    // Place an order
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    // Add order details
    public OrderDetails addOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    // Get order status by ID
    public String getOrderStatus(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"))
                .getOrderStatus();
    }

    // Get an order by ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
