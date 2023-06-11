package com.saga.inventory.service;

import com.saga.inventory.enumerated.InventoryStatus;
import com.saga.inventory.repository.OrderRepository;
import com.saga.inventory.repository.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order updateOrderStatus(String createdOrderId, InventoryStatus status) {
        Order order = orderRepository.findById(createdOrderId).get();
        order.setOrderStatus(status.toString());
        return orderRepository.save(order);
    }
}
