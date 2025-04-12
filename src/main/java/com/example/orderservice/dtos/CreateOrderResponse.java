package com.example.orderservice.dtos;

import com.example.orderservice.models.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {

    private Order order;
    private String paymentLink;

}
