package com.saga.inventory.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ZeebeRequest {
    private String createdOrderId;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private Integer productCount;
    private String orderStatus;
}
