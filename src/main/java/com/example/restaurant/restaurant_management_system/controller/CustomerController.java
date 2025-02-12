package com.example.restaurant.restaurant_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restaurant.restaurant_management_system.model.Menu;
import com.example.restaurant.restaurant_management_system.model.Order;
import com.example.restaurant.restaurant_management_system.model.OrderDetails;
import com.example.restaurant.restaurant_management_system.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Get the menu
    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getMenu() {
        List<Menu> menuItems = customerService.getMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    // Place an order
    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order placedOrder = customerService.placeOrder(order);
        return ResponseEntity.ok(placedOrder);
    }

    // Add order details
    @PostMapping("/orders/details")
    public ResponseEntity<OrderDetails> addOrderDetails(@RequestBody OrderDetails orderDetails) {
        OrderDetails details = customerService.addOrderDetails(orderDetails);
        return ResponseEntity.ok(details);
    }

    // Get the status of an order
    @GetMapping("/orders/{orderId}/status")
    public ResponseEntity<String> getOrderStatus(@PathVariable Long orderId) {
        String status = customerService.getOrderStatus(orderId);
        return ResponseEntity.ok(status);
    }

    // Get an order by ID
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = customerService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
