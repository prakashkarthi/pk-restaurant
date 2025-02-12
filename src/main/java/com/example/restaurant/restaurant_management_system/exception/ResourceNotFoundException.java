package com.example.restaurant.restaurant_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Added serialVersionUID

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
