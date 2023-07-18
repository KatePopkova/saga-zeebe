package com.saga.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.order.controller.dto.OrderRequest;
import com.saga.order.enumerated.OrderStatus;
import com.saga.order.repository.OrderRepository;
import com.saga.order.repository.entity.Order;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final ZeebeService zeebeService;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public Order createOrder(OrderRequest orderRequest) throws JsonProcessingException {
        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .productId(orderRequest.getProductId())
                .price(orderRequest.getPrice())
                .productCount(orderRequest.getProductCount())
                .orderStatus(OrderStatus.CREATED.toString())
                .build();

        orderRepository.save(order);
        String variablesResult = zeebeService.start(order).getVariables();
        String orderStatus  = objectMapper.readValue(variablesResult, Order.class).getOrderStatus();

        if(orderStatus.equals(OrderStatus.RESERVED.toString())) {
            order.setOrderStatus(OrderStatus.COMPLETED.toString());
        }
        if(orderStatus.equals(OrderStatus.REJECTED.toString())) {
            order.setOrderStatus(OrderStatus.CANCELLED.toString());
        }

        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
