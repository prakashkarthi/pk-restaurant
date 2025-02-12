package com.example.restaurant.restaurant_management_system.service;

import com.example.restaurant.restaurant_management_system.dto.MenuDTO;
import com.example.restaurant.restaurant_management_system.exception.ResourceNotFoundException;
import com.example.restaurant.restaurant_management_system.model.Menu;
import com.example.restaurant.restaurant_management_system.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminService {

    @Autowired
    private MenuRepository menuRepository;
    
    public MenuDTO convertToMenuDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setItemName(menu.getItemName());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setCategory(menu.getCategory());
        return menuDTO;
    }

    public Menu convertToMenuEntity(MenuDTO menuDTO) {
        Menu menu = new Menu();
        menu.setId(menuDTO.getId());
        menu.setItemName(menuDTO.getItemName());
        menu.setDescription(menuDTO.getDescription());
        menu.setPrice(menuDTO.getPrice());
        menu.setCategory(menuDTO.getCategory());
        return menu;
    }


    // Get all menu items
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }
    
    public Menu getMenuItemById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Menu not found for id: " + id));
    }

    // Add a new menu item
    public void addMenuItem(Menu menu) {
        menuRepository.save(menu); // Saves the menu item to the database
    }

    // Update an existing menu item
    public Menu updateMenuItem(Long id, Menu menu) {
        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with ID: " + id));
        existingMenu.setItemName(menu.getItemName());
        existingMenu.setPrice(menu.getPrice());
        existingMenu.setAvailability(menu.isAvailability());
        return menuRepository.save(existingMenu);
    }

    // Delete a menu item
    public void deleteMenuItem(Long id) {
        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with ID: " + id));
        menuRepository.delete(existingMenu);
    }
}
