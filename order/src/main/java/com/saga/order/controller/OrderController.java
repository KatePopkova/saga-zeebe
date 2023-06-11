package com.saga.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saga.order.controller.dto.OrderRequest;
import com.saga.order.repository.entity.Order;
import com.saga.order.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class OrderController {
    private final OrderProductService orderProductService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) throws JsonProcessingException {
        return ResponseEntity.ok(orderProductService.createOrder(orderRequest));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderProductService.getOrders());
    }
}
