package com.example.restaurant.repository;

import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
	
}
