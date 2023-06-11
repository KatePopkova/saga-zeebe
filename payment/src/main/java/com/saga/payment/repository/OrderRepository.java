package com.saga.payment.repository;

import com.saga.payment.repository.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
