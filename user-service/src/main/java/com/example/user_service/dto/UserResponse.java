package com.example.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @NotBlank(message = "user name is required")
    private String userName;
    @NotBlank(message = "password is required")
    private String password;
    @Email
    private String email;
    private Integer number;
}
