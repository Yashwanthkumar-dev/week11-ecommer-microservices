package com.example.productServices.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.productServices.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService service;

    // ============================ create product =========================
    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam("name") String productName,
            @RequestParam("desc") String description,
            @RequestParam("price") Double price,
            @RequestParam("Stock") Integer stock,
            @RequestParam("image") MultipartFile image) throws IOException {
        return service.createProduct(
                productName,
                description,
                price,
                stock,
                image);
    }

    // ========================= view all products ==================
    @GetMapping
    public ResponseEntity<?> viewAllProducts() {
        return service.viewAllProducts();
    }

    // ======================= get product by id =================
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable Long id) {
        return service.getProductById(id);
    }

    // ======================== update product by id ================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(
            @PathVariable Long id,
            @RequestParam("name") String productName,
            @RequestParam("desc") String description,
            @RequestParam("price") Double price,
            @RequestParam("Stock") Integer stock,
            @RequestParam("image") MultipartFile image) {
        return service.updateProductById(
                id,
                productName,
                description,
                price,
                stock,
                image);
    }

    // ====================== delete product by id ================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        return service.deleteProductById(id);
    }
}