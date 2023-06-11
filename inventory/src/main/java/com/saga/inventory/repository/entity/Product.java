package com.saga.inventory.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private Integer productId;
    private Integer productCount;
}
