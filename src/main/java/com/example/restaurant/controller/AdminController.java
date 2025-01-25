package com.example.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/admin/menu")
public class AdminController {

    @Autowired
    private MenuService menuService;

    // Get all menu items
    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenuItems() {
        return ResponseEntity.ok(menuService.getAllMenuItems());
    }

    // Get a single menu item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuItemById(id));
    }

    // Add or update a menu item
    @PostMapping
    public ResponseEntity<Menu> addOrUpdateMenuItem(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.saveOrUpdateMenuItem(menu));
    }

    // Delete a menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
        return ResponseEntity.ok("Menu item deleted successfully");
    }
}
