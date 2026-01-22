package com.example.dto;

public record NewOrderEvent(
        String orderId,
        String userId,
        double amount
) {
}
