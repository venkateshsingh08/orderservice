package com.example.orderservice.services;

import com.example.orderservice.dtos.CreateOrderRequest;
import com.example.orderservice.dtos.CreateOrderResponse;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
