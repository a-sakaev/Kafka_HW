package com.example.dto;

public record SentOrderEvent(
        String orderId,
        boolean sent
) {
}
