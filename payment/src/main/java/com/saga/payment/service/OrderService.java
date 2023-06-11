package com.saga.payment.service;

import com.saga.payment.enumerated.PaymentStatus;
import com.saga.payment.repository.OrderRepository;
import com.saga.payment.repository.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order updateOrderStatus(String createdOrderId, PaymentStatus status) {
        Order order = orderRepository.findById(createdOrderId).get();
        order.setOrderStatus(status.toString());
        return orderRepository.save(order);
    }
}
