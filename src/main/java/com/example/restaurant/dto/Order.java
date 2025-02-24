//package com.example.restaurant.dto;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.util.List;
//
//import com.example.restaurant.entity.OrderDetails;
//
//@Entity
//@Table(name = "orders")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String customerName;
//
//    @Column(nullable = false)
//    private String contactNumber;
//
//    @Column(nullable = false)
//    private String deliveryOption;
//
//    @Column(nullable = false)
//    private String status;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<OrderDetails> orderDetails;
//}
