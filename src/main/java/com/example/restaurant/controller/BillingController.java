package com.example.restaurant.controller;

import com.example.restaurant.entity.Order;
import com.example.restaurant.service.BillingService;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private OrderService orderService;

    /**
     * Endpoint to get the total bill for an order.
     *
     * @param orderId ID of the order.
     * @return ResponseEntity containing the total amount.
     */
    @GetMapping("/bill/{orderId}")
    public ResponseEntity<Double> getBill(@PathVariable Long orderId) {
        try {
            Order order = orderService.getOrderById(orderId);
            double totalAmount = billingService.calculateTotalBill(order);
            return ResponseEntity.ok(totalAmount);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Endpoint to generate an invoice for an order.
     *
     * @param orderId ID of the order.
     * @return ResponseEntity containing the invoice as a string.
     */
    @GetMapping("/invoice/{orderId}")
    public ResponseEntity<String> generateInvoice(@PathVariable Long orderId) {
        try {
            Order order = orderService.getOrderById(orderId);
            String invoice = billingService.generateInvoice(order);
            return ResponseEntity.ok(invoice);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    /**
     * Endpoint to update the billing status of an order (e.g., mark as paid).
     *
     * @param orderId ID of the order.
     * @param status  New billing status for the order.
     * @return ResponseEntity containing the updated Order.
     */
    @PutMapping("/status/{orderId}")
    public ResponseEntity<Order> updateBillingStatus(@PathVariable Long orderId, @RequestParam String status) {
        try {
            if (status == null || status.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            Order order = orderService.getOrderById(orderId);
            order.setStatus(status);
            Order updatedOrder = orderService.updateOrder(orderId, order);
            return ResponseEntity.ok(updatedOrder);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
