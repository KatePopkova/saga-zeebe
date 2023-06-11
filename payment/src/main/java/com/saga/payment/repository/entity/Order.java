package com.saga.payment.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
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
