package com.example.order_services.exception;

public class OrderNotFoudException extends RuntimeException{

    public OrderNotFoudException(String message){
        super(message);
    }
}
