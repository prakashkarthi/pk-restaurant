package com.example.restaurant.service;

import com.example.restaurant.dto.OrderDTO;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderDetails;
import com.example.restaurant.exception.ResourceNotFoundException;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.OrderDetailsRepository;
import com.example.restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    /**
     * Creates a new order from the provided OrderDTO.
     *
     * @param orderDTO OrderDTO containing the order details.
     * @return the created OrderDTO.
     */
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setContactNumber(orderDTO.getContactNumber());
        order.setStatus("PENDING"); // Explicitly set status
        order.setDeliveryOption(orderDTO.getDeliveryOption());

        double totalAmount = 0.0;
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDTO.OrderItemDTO itemDTO : orderDTO.getItems()) {
            Menu menu = menuRepository.findById(itemDTO.getMenuId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setMenu(menu);
            orderDetail.setOrder(order);
            orderDetail.setQuantity(itemDTO.getQuantity());
            orderDetailsList.add(orderDetail);

            totalAmount += menu.getPrice() * itemDTO.getQuantity();
        }

        order.setOrderDetails(orderDetailsList);
        order.setTotalAmount(totalAmount);

        // Ensure both order and order details are saved
        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }
    /**
     * Fetches all orders as DTOs.
     *
     * @return List of OrderDTOs.
     */
    public List<OrderDTO> getAllOrdersAsDTO() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToDTO).toList();
    }

    /**
     * Fetches an order by its ID as a DTO.
     *
     * @param id the ID of the order.
     * @return the OrderDTO.
     */
    public OrderDTO getOrderByIdAsDTO(Long id) {
        Order order = getOrderById(id);
        return mapToDTO(order);
    }

    /**
     * Updates an order with the provided OrderDTO.
     *
     * @param id       the ID of the order to update.
     * @param orderDTO the updated OrderDTO.
     * @return the updated OrderDTO.
     */
    public OrderDTO updateOrderAsDTO(Long id, OrderDTO orderDTO) {
        Order order = getOrderById(id);
        order.setCustomerName(orderDTO.getCustomerName());
        order.setContactNumber(orderDTO.getContactNumber());
        order.setDeliveryOption(orderDTO.getDeliveryOption());
        order.setStatus("UPDATED");

        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }

    /**
     * Updates an order with the provided entity details.
     *
     * @param orderId the ID of the order to update.
     * @param order   the updated order entity.
     * @return the updated Order entity.
     */
    public Order updateOrder(Long orderId, Order order) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setContactNumber(order.getContactNumber());
        existingOrder.setDeliveryOption(order.getDeliveryOption());
        existingOrder.setStatus(order.getStatus());
        return orderRepository.save(existingOrder);
    }

    /**
     * Fetches orders by their status.
     *
     * @param statuses List of statuses to filter by.
     * @return List of matching orders.
     */
    public List<Order> getOrdersByStatus(List<String> statuses) {
        return orderRepository.findByStatusIn(statuses);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete.
     */
    public void deleteOrder(Long orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }

    /**
     * Saves or updates an order.
     *
     * @param order the Order entity to save or update.
     * @return the saved Order entity.
     */
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Utility method to map an Order entity to an OrderDTO.
     *
     * @param order the Order entity.
     * @return the corresponding OrderDTO.
     */
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

    /**
     * Fetches all orders.
     *
     * @return List of all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Fetches an order by its ID.
     *
     * @param orderId the ID of the order.
     * @return the Order entity.
     */
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
    }
}