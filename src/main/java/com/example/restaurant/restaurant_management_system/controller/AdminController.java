package com.example.restaurant.restaurant_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.restaurant.restaurant_management_system.model.Menu;
import com.example.restaurant.restaurant_management_system.service.AdminService;


@Controller
@RequestMapping("/admin/menu")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Show all menu items
    @GetMapping("/add")
    public String showAddMenuItemForm(Model model) {
        model.addAttribute("menuItem", new Menu());
        return "menu-add"; // Return the view where the form is located
    }

    @PostMapping("/add")
    public String addMenuItem(@ModelAttribute Menu menuItem) {
        adminService.addMenuItem(menuItem); // Service method to save the menu item
        return "redirect:/admin/menu/list"; // Redirect to a page that shows all menu items
    }

    // Show form to edit a menu item
    @GetMapping("/edit/{id}")
    public String showEditMenuForm(@PathVariable Long id, Model model) {
        Menu menu = adminService.getMenuItemById(id);
        model.addAttribute("menu", menu);
        return "admin/edit_menu";
    }

    // Handle form submission for editing a menu item
    @PostMapping("/edit/{id}")
    public String updateMenuItem(@PathVariable Long id, @ModelAttribute Menu menu) {
        adminService.updateMenuItem(id, menu);
        return "redirect:/admin/menu/all";
    }

    // Delete a menu item
    @GetMapping("/delete/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        adminService.deleteMenuItem(id);
        return "redirect:/admin/menu/all";
    }
}
