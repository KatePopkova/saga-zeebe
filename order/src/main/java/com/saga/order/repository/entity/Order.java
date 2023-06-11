package com.saga.order.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private Integer productCount;
    private String orderStatus;
}
