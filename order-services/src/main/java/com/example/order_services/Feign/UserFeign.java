package com.example.order_services.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order_services.dto.UserResponse;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {

    @GetMapping("/api/users/{id}")
    UserResponse getUserById(@PathVariable Long id);
}
