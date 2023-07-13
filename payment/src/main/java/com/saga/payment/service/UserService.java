package com.saga.payment.service;

import com.saga.payment.repository.UserRepository;
import com.saga.payment.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUser(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    public User decreaseUserBalance(User user, Integer productValue) {
        user.setBalance(user.getBalance() - productValue);
        return userRepository.save(user);
    }
}