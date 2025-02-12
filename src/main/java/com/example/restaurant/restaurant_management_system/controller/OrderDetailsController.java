package com.example.restaurant.restaurant_management_system.controller;

import com.example.restaurant.restaurant_management_system.model.OrderDetails;
import com.example.restaurant.restaurant_management_system.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    // Create or update order details
    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails orderDetails) {
        OrderDetails savedOrderDetails = orderDetailsService.saveOrderDetails(orderDetails);
        return new ResponseEntity<>(savedOrderDetails, HttpStatus.CREATED);
    }

    // Get OrderDetails by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Long id) {
        Optional<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsById(id);
        if (orderDetails.isPresent()) {
            return new ResponseEntity<>(orderDetails.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all order details for a specific order
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetails>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetails> orderDetailsList = orderDetailsService.getOrderDetailsByOrderId(orderId);
        return new ResponseEntity<>(orderDetailsList, HttpStatus.OK);
    }

    // Get all order details (if needed)
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsService.getAllOrderDetails();
        return new ResponseEntity<>(orderDetailsList, HttpStatus.OK);
    }

    // Delete order details by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
