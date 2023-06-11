package com.saga.payment.exception;

public class BusinessException extends Exception {
    private String message;

    public BusinessException(String message) {
        this.message = message;
    }
}
