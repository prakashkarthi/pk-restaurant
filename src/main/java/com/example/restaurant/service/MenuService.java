package com.example.restaurant.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.repository.MenuRepository;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // Get all menu items
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll(); // Fetches all menu items from the database
    }

    // Get a single menu item by ID
    public Menu getMenuItemById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }
    
    public List<Menu> saveAll(List<Menu> menus) {
        return menuRepository.saveAll(menus);
    }

    // Add or update a menu item
    public Menu saveOrUpdateMenuItem(Menu menu) {
        return menuRepository.save(menu);
    }

    // Delete a menu item
    public void deleteMenuItem(Long id) {
        menuRepository.deleteById(id);
    }

	public Menu saveMenuItem(Menu menu) {
		return menuRepository.save(menu);
	}
}
