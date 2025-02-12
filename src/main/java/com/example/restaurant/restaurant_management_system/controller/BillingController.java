package com.example.restaurant.restaurant_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restaurant.restaurant_management_system.dto.OrderDetailsDTO;
import com.example.restaurant.restaurant_management_system.service.BillingService;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    // Get the total bill for an order
    @GetMapping("/orders/{orderId}/total")
    public ResponseEntity<Double> getBill(@PathVariable Long orderId) {
        double total = billingService.calculateBill(orderId);
        return ResponseEntity.ok(total);
    }

    // Get order details for billing
    @GetMapping("/orders/{orderId}/details")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails(@PathVariable Long orderId) {
        List<OrderDetailsDTO> orderDetails = billingService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDetails);
    }
}
