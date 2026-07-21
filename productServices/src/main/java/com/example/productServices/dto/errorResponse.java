package com.example.productServices.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class errorResponse {
    private String Message;
    private String error;
    private int status;
    private LocalDateTime timeStamp;
}
