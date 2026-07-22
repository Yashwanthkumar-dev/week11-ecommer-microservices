package com.example.user_service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    // ============== reuse code =============
    public UserEntity getUserOrThrow(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("user was not found in this id"));
    }

    // =========== creating user ================
    public ResponseEntity<?> createUser(UserEntity user) {
        Boolean exists = userRepo.existsByUserName(user.getUserName());
        if (exists != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("user was already exists");
        }
        user.setCreatedAt(LocalDateTime.now());
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
    }

    // ================ get all users ====================
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> allUsers = userRepo.findAll();
        return ResponseEntity.ok(allUsers);
    }

    // ============== get user by id ==================
    public ResponseEntity<?> getAllUserById(Long id) {
        UserEntity isUser = getUserOrThrow(id);
        return ResponseEntity.status(HttpStatus.OK).body(isUser);
    }

    // ============ udpate user by id =================
    public ResponseEntity<?> udpateUserDetails(Long id, UserEntity user) {
        UserEntity isUser = getUserOrThrow(id);
        isUser.setUdpatedAt(LocalDateTime.now());
        isUser.setEmail(user.getEmail());
        isUser.setNumber(user.getNumber());
        isUser.setPassword(user.getPassword());
        isUser.setUserName(user.getUserName());
        userRepo.save(isUser);
        return ResponseEntity.status(HttpStatus.OK).body("updated successfully");
    }

    // ================= delete user by id ==================
    public ResponseEntity<?> deleteUserById(Long id) {
        UserEntity isUser = getUserOrThrow(id);
        userRepo.delete(isUser);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");

    }

}
