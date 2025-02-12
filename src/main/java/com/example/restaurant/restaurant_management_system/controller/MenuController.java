package com.example.restaurant.restaurant_management_system.controller;

import com.example.restaurant.restaurant_management_system.model.Menu;
import com.example.restaurant.restaurant_management_system.service.MenuService;

import ch.qos.logback.core.model.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/menu")  // Change the base path to be more specific
public class MenuController {

    @Autowired
    private MenuService menuService;

    
    @PostMapping("/add-admin")
    public ResponseEntity<Menu> addMenuItem(@ModelAttribute Menu menu) {
        Menu addedMenuItem = menuService.addMenuItem(menu);
        return new ResponseEntity<>(addedMenuItem, HttpStatus.CREATED);
    }

    // Get all menu items
    @GetMapping("/")
    public ResponseEntity<List<Menu>> getAllMenuItems() {
        List<Menu> menuItems = menuService.getAllMenuItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    // Get a single menu item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuItemById(@PathVariable Long id) {
        Optional<Menu> menuItem = menuService.getMenuItemById(id);
        if (menuItem.isPresent()) {
            return new ResponseEntity<>(menuItem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing menu item
    @PutMapping("/update/{id}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable Long id, @RequestBody Menu menu) {
        Optional<Menu> updatedMenuItem = menuService.updateMenuItem(id, menu);
        if (updatedMenuItem.isPresent()) {
            return new ResponseEntity<>(updatedMenuItem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a menu item
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        if (menuService.deleteMenuItem(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
