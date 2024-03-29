package com.saga.payment.service;

import com.saga.payment.enumerated.PaymentStatus;
import com.saga.payment.exception.BusinessException;
import com.saga.payment.repository.entity.User;
import com.saga.payment.worker.dto.ZeebeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PaymentService {
    private final UserService userService;
    private final OrderService orderService;

    public ZeebeRequest payProducts(ZeebeRequest zeebeRequest) throws BusinessException {
        try {
            User user = userService.findUser(zeebeRequest.getUserId()).get();
            Integer productValue = zeebeRequest.getPrice() * zeebeRequest.getProductCount();
            if (user.getBalance() >= productValue) {
                userService.decreaseUserBalance(user, productValue);
                orderService.updateOrderStatus(zeebeRequest.getId(), PaymentStatus.RESERVED);
                zeebeRequest.setOrderStatus(PaymentStatus.RESERVED.toString());
            } else {
                throw new BusinessException("User does not have enough money for buying the product.");
            }
            return zeebeRequest;
        } catch (NoSuchElementException ex) {
            System.out.println("There is no order or user with provided ID. \n");
            throw new NoSuchElementException();
        }
    }

    public ZeebeRequest cancelProductPaying(ZeebeRequest zeebeRequest) {
        orderService.updateOrderStatus(zeebeRequest.getId(), PaymentStatus.REJECTED);
        zeebeRequest.setOrderStatus(PaymentStatus.REJECTED.toString());
        return zeebeRequest;
    }
}
