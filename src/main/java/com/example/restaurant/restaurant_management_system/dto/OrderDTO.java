package com.example.restaurant.restaurant_management_system.dto;

import java.util.List;

public class OrderDTO {
    private Long id;                    // Unique identifier for the order
    private String customerName;        // Name of the customer
    private String orderStatus;         // Status of the order (e.g., Pending, Completed)
    private List<OrderDetailsDTO> orderDetails;  // Details about the items in the order
    private Double totalAmount;         // Total price for the order

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetailsDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
