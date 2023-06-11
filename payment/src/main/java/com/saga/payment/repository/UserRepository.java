package com.saga.payment.repository;

import com.saga.payment.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserId(Integer userId);
}
