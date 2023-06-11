package com.saga.payment.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private Integer userId;
    private Integer balance;
}
