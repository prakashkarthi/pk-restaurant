package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restaurant.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	// Find menu items by availability
    List<Menu> findByAvailability(boolean availability);

    // Find menu items by name (case-insensitive)
    List<Menu> findByItemNameIgnoreCase(String itemName);

    // Find menu items with price less than or equal to a specific value
    List<Menu> findByPriceLessThanEqual(double price);

    // Custom JPQL query to fetch menu items sorted by price (ascending)
    @Query("SELECT m FROM Menu m ORDER BY m.price ASC")
    List<Menu> findAllSortedByPrice();
}
