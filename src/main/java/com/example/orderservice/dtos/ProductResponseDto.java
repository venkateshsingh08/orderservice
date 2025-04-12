package com.example.orderservice.dtos;

import com.example.orderservice.models.OrderItem;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    public OrderItem toOrderItem(int quantity) {
        OrderItem item = new OrderItem();
        item.setProductId(this.id);
        item.setPrice(this.price);
        item.setQuantity(quantity);
        return item;
    }


}
