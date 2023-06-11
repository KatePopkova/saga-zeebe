package com.saga.order.service;

import com.saga.order.controller.dto.OrderRequest;
import com.saga.order.enumerated.OrderStatus;
import com.saga.order.repository.OrderRepository;
import com.saga.order.repository.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order saveOrder(OrderRequest orderRequest) {
        return orderRepository.save(Order.builder()
                .userId(orderRequest.getUserId())
                .productId(orderRequest.getProductId())
                .price(orderRequest.getPrice())
                .productCount(orderRequest.getProductCount())
                .orderStatus(OrderStatus.CREATED.toString())
                .build());
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
