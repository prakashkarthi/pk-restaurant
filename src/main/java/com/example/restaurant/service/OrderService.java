package com.example.restaurant.service;

import com.example.restaurant.dto.OrderDTO;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderDetails;
import com.example.restaurant.exception.ResourceNotFoundException;
import com.example.restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object must not be null");
        }
        
        // Ensure the order is new and doesn't already exist
        if (order.getId() != null) {
            throw new IllegalArgumentException("New orders should not have an ID set.");
        }
        
        System.out.println("inside crearte order fun");
        return orderRepository.save(order);
    }

    public List<OrderDTO> getAllOrdersAsDTO() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToDTO).toList();
    }

    public OrderDTO getOrderByIdAsDTO(Long id) {
        Order order = getOrderById(id);
        return mapToDTO(order);
    }

    public OrderDTO updateOrderAsDTO(Long id, OrderDTO orderDTO) {
        Order order = getOrderById(id);
        order.setCustomerName(orderDTO.getCustomerName());
        order.setContactNumber(orderDTO.getContactNumber());
        order.setDeliveryOption(orderDTO.getDeliveryOption());
        order.setStatus("UPDATED");

        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }

    public Order updateOrder(Long orderId, Order order) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setContactNumber(order.getContactNumber());
        existingOrder.setDeliveryOption(order.getDeliveryOption());
        existingOrder.setStatus(order.getStatus());
        return orderRepository.save(existingOrder);
    }

    public List<Order> getOrdersByStatus(List<String> statuses) {
        return orderRepository.findByStatusIn(statuses);
    }

    public void deleteOrder(Long orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setContactNumber(order.getContactNumber());
        orderDTO.setDeliveryOption(order.getDeliveryOption());
        orderDTO.setTotalAmount(order.getTotalAmount());

        List<OrderDTO.OrderItemDTO> itemDTOs = new ArrayList<>();
        for (OrderDetails details : order.getOrderDetails()) {
            OrderDTO.OrderItemDTO itemDTO = new OrderDTO.OrderItemDTO();
            itemDTO.setMenuId(details.getMenu().getId());
            itemDTO.setQuantity(details.getQuantity());
            itemDTOs.add(itemDTO);
        }
        orderDTO.setItems(itemDTOs);

        return orderDTO;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
    }
}
