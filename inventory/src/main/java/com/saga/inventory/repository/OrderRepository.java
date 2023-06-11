package com.saga.inventory.repository;

import com.saga.inventory.repository.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
