package com.saga.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.order.controller.dto.OrderRequest;
import com.saga.order.repository.entity.Order;
import com.saga.order.service.dto.ZeebeOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final ZeebeService zeebeService;
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public Order createOrder(OrderRequest orderRequest) throws JsonProcessingException {
        Order order = orderService.saveOrder(orderRequest);

        ZeebeOrderRequest zeebeOrderRequest = ZeebeOrderRequest.builder()
                .createdOrderId(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .price(order.getPrice())
                .productCount(orderRequest.getProductCount())
                .orderStatus(order.getOrderStatus())
                .build();

        zeebeService.start(objectMapper.writeValueAsString(zeebeOrderRequest));
        return order;
    }

    public List<Order> getOrders() {
        return orderService.findAllOrders();
    }
}
