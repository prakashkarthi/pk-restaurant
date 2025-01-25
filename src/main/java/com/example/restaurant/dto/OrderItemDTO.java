package com.example.restaurant.dto;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Order;

@Entity
@Table(name = "order_items")
public class OrderItemDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private Menu menuItem;

    private int quantity;
    private BigDecimal price;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Menu getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(Menu menuItem) {
		this.menuItem = menuItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

    // Getters and setters
}