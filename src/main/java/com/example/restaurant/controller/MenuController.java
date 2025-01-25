package com.example.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.service.MenuService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")  // Replace with the URL of your frontend
@RequestMapping("/customer")
public class MenuController {

    @Autowired
    private MenuService menuService;
    
    // Method to fetch all menu items
    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getAllMenuItems() {
        List<Menu> menu = menuService.getAllMenuItems();
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
    
    // Method for adding a single menu item (changed path to "/menu/item")
    @PostMapping("/menu/item")  // Updated to "/item"
    public ResponseEntity<Menu> addMenuItem(@RequestBody Menu menu) {
        Menu savedMenu = menuService.saveMenuItem(menu);
        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);
    }
    
    // Method for adding multiple menu items (remains "/menu")
    @PostMapping("/menu")  // Path remains "/menu"
    public ResponseEntity<List<Menu>> addMenuItems(@RequestBody List<Menu> menus) {
        List<Menu> savedMenus = menuService.saveAll(menus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMenus);
    }
    
    // Method for updating a menu item
    @PutMapping("/menu/{id}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable Long id, @RequestBody Menu updatedMenu) {
        // Fetch the existing menu item
        Menu existingMenu = menuService.getMenuItemById(id);

        if (existingMenu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Update fields
        existingMenu.setItemName(updatedMenu.getItemName());
        existingMenu.setPrice(updatedMenu.getPrice());
        existingMenu.setDescription(updatedMenu.getDescription());
        existingMenu.setAvailability(updatedMenu.isAvailability());

        // Save the updated menu item
        Menu savedMenu = menuService.saveMenuItem(existingMenu);

        return ResponseEntity.ok(savedMenu);
    }
}
