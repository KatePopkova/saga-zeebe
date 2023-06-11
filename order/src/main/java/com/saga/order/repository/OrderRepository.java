package com.saga.order.repository;

import com.saga.order.repository.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository  extends MongoRepository<Order, String> {
}
