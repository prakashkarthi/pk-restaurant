package com.example.restaurant.controller;

import com.example.restaurant.entity.Order;
import com.example.restaurant.service.ChefService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chef")
public class ChefController {

    private final ChefService chefService;
    private final OrderService orderService;

    @Autowired
    public ChefController(ChefService chefService, OrderService orderService) {
        this.chefService = chefService;
        this.orderService = orderService;
    }

    // Main page for chef
    @GetMapping
    public String getChefHomePage() {
        return "chef/index"; // Ensure chef/index.html exists in templates/chef/
    }

    // View all orders that are in "Pending" or "In Progress" status
    @GetMapping("/orders")
    public String viewOrders(Model model) {
        // Define the statuses you want to filter
        List<String> statuses = List.of("PENDING", "IN_PROGRESS");
        // Fetch the orders with the specified statuses
        List<Order> orders = orderService.getOrdersByStatus(statuses);
        // Add orders to the model to pass them to the view
        model.addAttribute("orders", orders);
        return "chef/orders"; // Refers to the Thymeleaf template for displaying orders
    }


    // View details of a specific order
    @GetMapping("/order/{orderId}")
    public String viewOrderDetails(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            model.addAttribute("order", order);
            return "chef/orderDetails"; // Ensure chef/orderDetails.html exists
        }
        model.addAttribute("error", "Order not found!");
        return "chef/orders"; // Redirect or show error on orders page
    }

    // Update the status of an order
    @PostMapping("/update-status/{orderId}")
    public String updateOrderStatus(@PathVariable("orderId") Long orderId,
                                    @RequestParam("status") String status, Model model) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            orderService.save(order);
        } else {
            model.addAttribute("error", "Failed to update order status: Order not found.");
        }
        return "redirect:/chef/orders";
    }

    // View orders filtered by specific status
    @GetMapping("/orders/status/{status}")
    public String viewOrdersByStatus(@PathVariable("status") String status, Model model) {
        List<Order> orders = orderService.getOrdersByStatus(List.of(status));
        model.addAttribute("orders", orders);
        model.addAttribute("status", status); // Optional: display current filter status
        return "chef/orders"; // Ensure chef/orders.html exists
    }
}
