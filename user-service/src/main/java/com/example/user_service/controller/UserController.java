package com.example.user_service.controller;

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

import com.example.user_service.entity.UserEntity;
import com.example.user_service.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    // =========== creating user ================
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        return service.createUser(user);
    }

    // =============== get all users ==================
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return service.getAllUsers();
    }

    // ============== get user by id ==================
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return service.getAllUserById(id);
    }

    // ============ udpate user by id =================
    @PutMapping("/{id}")
    public ResponseEntity<?> udpateUserDetails(@PathVariable Long id, @RequestBody UserEntity user) {
        return service.udpateUserDetails(id, user);
    }

    // ================= delete user by id ==================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }
}
