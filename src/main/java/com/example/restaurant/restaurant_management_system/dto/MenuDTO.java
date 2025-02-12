package com.example.restaurant.restaurant_management_system.dto;

public class MenuDTO {
    private Long id;                // Unique identifier
    private String itemName;        // Name of the menu item
    private String description;     // Description of the item
    private Double price;           // Price of the item
    private String category;        // Category (e.g., Appetizer, Main Course)

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
