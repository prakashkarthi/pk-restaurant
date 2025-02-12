package com.example.restaurant.restaurant_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/admin/menu")
public class MenuPageController {

    @GetMapping("/add-menu")
    public String showAddMenuForm(Model model) {
        return "add-menu";  // Returns the view named 'add-menu.html'
    }
}

