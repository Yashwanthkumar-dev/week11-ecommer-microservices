package com.example.order_services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_services.dto.OrderRequestDto;
import com.example.order_services.services.OrderService;

@RequestMapping("/api/orders")
@RestController
public class OrderController {
    @Autowired
    private OrderService service;

    // ==================== place order ====================
    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto request) {
        return service.placeOrder(request);
    }

    // ================== get All orders ====================
    @GetMapping
    public ResponseEntity<?> viewAllOrders() {
        return service.viewAllOrder();
    }

    // ================== get order by id ================
    @GetMapping("/{id}")
    public ResponseEntity<?> viewOrderById(@PathVariable Long id) {
        return service.viewOrderById(id);
    }

    // ================== update order by id =================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderByid(@PathVariable Long id, @RequestBody OrderRequestDto request) {
        return service.updateOrderById(id, request);
    }

    // ====================== delete order by id ==================
    @DeleteMapping("/{id")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        return service.deleteOrderById(id);
    }
}
