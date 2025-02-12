package com.example.restaurant.restaurant_management_system.service;



import com.example.restaurant.restaurant_management_system.model.Menu;
import com.example.restaurant.restaurant_management_system.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // Add a new menu item
    public Menu addMenuItem(Menu menu) {
        return menuRepository.save(menu);
    }

    // Get all menu items
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }

    // Get a menu item by its ID
    public Optional<Menu> getMenuItemById(Long id) {
        return menuRepository.findById(id);
    }

    // Update an existing menu item
    public Optional<Menu> updateMenuItem(Long id, Menu menu) {
        if (menuRepository.existsById(id)) {
            menu.setId(id);
            return Optional.of(menuRepository.save(menu));
        } else {
            return Optional.empty();
        }
    }

    // Delete a menu item
    public boolean deleteMenuItem(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

