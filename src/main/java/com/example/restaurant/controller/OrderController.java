package com.example.restaurant.controller;

import com.example.restaurant.dto.OrderDTO;
import com.example.restaurant.entity.Order;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @PostMapping("/placeOrder")
//    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
//        if (order == null || order.getCustomerName() == null) {
//            return ResponseEntity.badRequest().body("Invalid order details");
//        }
//
//        Order newOrder = orderService.createOrder(order);
//        return ResponseEntity.ok(newOrder);
//    }
    
    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        if (order == null || order.getCustomerName() == null) {
            return ResponseEntity.badRequest().body("Invalid order details");
        }

        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        try {
            OrderDTO orderDTO = orderService.getOrderByIdAsDTO(id);
            return ResponseEntity.ok(orderDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO updatedOrder = orderService.updateOrderAsDTO(id, orderDTO);
            return ResponseEntity.ok(updatedOrder);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
