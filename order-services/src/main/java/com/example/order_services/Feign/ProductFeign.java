package com.example.order_services.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order_services.dto.ProductResponse;

@FeignClient(name = "PRODUCT-SERVICES")
public interface ProductFeign {

    @GetMapping("/api/product/{id}")
    ProductResponse getProductById(@PathVariable Long id);

}
