package com.example.restaurant.restaurant_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.restaurant_management_system.model.Menu;

@Repository
public interface AdminRepository extends JpaRepository<Menu, Long> {
    // Additional admin-specific queries can go here
}
