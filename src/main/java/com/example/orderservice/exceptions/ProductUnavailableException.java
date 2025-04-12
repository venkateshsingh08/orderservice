package com.example.orderservice.exceptions;

public class ProductUnavailableException extends   RuntimeException {

    public ProductUnavailableException(String message) {
        super(message);
    }

    public ProductUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

}
