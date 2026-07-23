package com.example.order_services.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.order_services.Feign.ProductFeign;
import com.example.order_services.Feign.UserFeign;
import com.example.order_services.dto.OrderRequestDto;
import com.example.order_services.dto.OrderResponseDto;
import com.example.order_services.dto.ProductResponse;
import com.example.order_services.dto.UserResponse;
import com.example.order_services.entity.OrderEntity;
import com.example.order_services.exception.OrderNotFoudException;
import com.example.order_services.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private UserFeign userFeign;

    // ======== Reuse code =============
    public OrderEntity getIdOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(
                        () -> new OrderNotFoudException("Order was not found in this id" + id));
    }

    // ==================== place order ====================
    public ResponseEntity<?> placeOrder(OrderRequestDto request) {

        UserResponse user = userFeign.getUserById(request.getUserId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user was not found");

        }
        ProductResponse product = productFeign.getProductById(request.getProductId());
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product was not found");
        }

        OrderEntity order = new OrderEntity();
        order.setProductId(product.getProductId());
        order.setUserId(user.getUserId());

        order.setQuantity(request.getQuantity());

        if (product.getStock() < request.getQuantity()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("insufficient storage");
        }
        double totalPrice = product.getPrice() * request.getQuantity();

        order.setTotalPrice(totalPrice);

        order.setCreatedAt(LocalDateTime.now());

        repo.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    // ================== get All orders ====================
    public ResponseEntity<?> viewAllOrder() {
        List<OrderEntity> orders = repo.findAll();

        List<OrderResponseDto> response = orders.stream()
                .map(
                        order -> {
                            OrderResponseDto responseDto = new OrderResponseDto();
                            responseDto.setOrderId(order.getId());
                            responseDto.setProductId(order.getProductId());
                            responseDto.setUserId(order.getUserId());
                            responseDto.setQuantity(order.getQuantity());
                            responseDto.setTotalPrice(order.getTotalPrice());
                            return responseDto;
                        })
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // ================== get order by id ================
    public ResponseEntity<?> viewOrderById(Long id) {
        OrderEntity order = getIdOrThrow(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    // ================== update order by id =================
    public ResponseEntity<?> updateOrderById(Long id, OrderRequestDto request) {
        UserResponse user = userFeign.getUserById(request.getUserId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user was not found");

        }
        ProductResponse product = productFeign.getProductById(request.getProductId());
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product was not found");
        }

        OrderEntity order = getIdOrThrow(id);
        if (product.getStock() < request.getQuantity()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("INSUFFICIENT STOCK");
        }
        order.setQuantity(request.getQuantity());
        order.setProductId(product.getProductId());
        order.setUserId(user.getUserId());

        double totalPrice = product.getPrice() * request.getQuantity();
        order.setTotalPrice(totalPrice);
        order.setUpdateAt(LocalDateTime.now());
        repo.save(order);
        return ResponseEntity.status(HttpStatus.OK)
                .body("updated succcessfully");
    }

    // ====================== delete order by id ==================
    public ResponseEntity<?> deleteOrderById(Long id) {
        OrderEntity order = getIdOrThrow(id);
        repo.delete(order);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    }

}
