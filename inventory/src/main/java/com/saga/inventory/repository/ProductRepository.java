package com.saga.inventory.repository;

import com.saga.inventory.repository.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductId(Integer productId);
}
