package com.example.restaurant.service;

import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderDetails;
import com.example.restaurant.entity.Menu;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    // Calculate the total bill for an order
    public double calculateTotalBill(Order order) {
        double totalAmount = 0;

        // Iterate over the order details to calculate the total cost
        for (OrderDetails orderDetail : order.getOrderDetails()) {
            Menu menuItem = orderDetail.getMenu();
            int quantity = orderDetail.getQuantity();
            totalAmount += menuItem.getPrice() * quantity; // Add price * quantity to total
        }

        return totalAmount;
    }

    // Generate a simple invoice (this could be expanded for a more detailed invoice)
    public String generateInvoice(Order order) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Invoice for Order ID: ").append(order.getId()).append("\n");
        invoice.append("Customer: ").append(order.getCustomerName()).append("\n");
        invoice.append("Contact: ").append(order.getContactNumber()).append("\n");
        invoice.append("Delivery Option: ").append(order.getDeliveryOption()).append("\n");
        invoice.append("Status: ").append(order.getStatus()).append("\n");
        invoice.append("\nOrder Details:\n");

        for (OrderDetails orderDetail : order.getOrderDetails()) {
            Menu menuItem = orderDetail.getMenu();
            int quantity = orderDetail.getQuantity();
            invoice.append(menuItem.getItemName()).append(" - ").append(quantity).append(" x ")
                    .append(menuItem.getPrice()).append(" = ").append(menuItem.getPrice() * quantity).append("\n");
        }

        double totalAmount = calculateTotalBill(order);
        invoice.append("\nTotal: ").append(totalAmount).append("\n");

        return invoice.toString();
    }
}
