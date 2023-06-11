package com.saga.order.service.dto;

import com.saga.order.controller.dto.OrderRequest;
import lombok.*;

@Getter
@NoArgsConstructor
public class ZeebeOrderRequest extends OrderRequest {
    private String orderStatus;
    private String createdOrderId;

    @Builder
    public ZeebeOrderRequest(String createdOrderId, Integer userId, Integer productId, Integer price, Integer productCount, String orderStatus) {
        super(userId, productId, price, productCount);
        this.orderStatus = orderStatus;
        this.createdOrderId = createdOrderId;
    }
}
