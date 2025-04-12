package com.example.orderservice.services;

import com.example.orderservice.dtos.CreateOrderRequest;
import com.example.orderservice.dtos.CreateOrderResponse;
import com.example.orderservice.dtos.ProductResponseDto;
import com.example.orderservice.exceptions.ProductUnavailableException;
import com.example.orderservice.models.Order;
import com.example.orderservice.models.OrderItem;
import com.example.orderservice.models.OrderStatus;
import com.example.orderservice.repositories.OrderRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService{

    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;



    public OrderServiceImpl(RestTemplate restTemplate,OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }


    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {


        List<OrderItem> items = new ArrayList<>();
        double total = 0.0;

        for (CreateOrderRequest.ItemRequest itemReq : request.getItems()) {

            String url = productServiceUrl + "/product/" + itemReq.getProductId();

            ProductResponseDto product = restTemplate.getForObject(url, ProductResponseDto.class);

            if (product == null) {
                throw new ProductUnavailableException("Product with ID " + itemReq.getProductId() + " not found");
            }

            double itemTotal = product.getPrice() * itemReq.getQuantity();

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setPrice(product.getPrice());
            item.setQuantity(itemReq.getQuantity());

            total += itemTotal;
            items.add(item);
        }

        Order order = new Order();

        order.setItems(items);
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        order = orderRepository.save(order);

        String paymentLink = restTemplate.postForObject(
                paymentServiceUrl + "/payment",
                null,
                String.class
        );


        return new CreateOrderResponse(order,paymentLink);
    }
}
