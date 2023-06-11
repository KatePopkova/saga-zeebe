package com.saga.order.controller.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Integer userId;
    private Integer productId;
    private Integer price;
    private Integer productCount;
}
