package com.example.dto;

public record PayedOrderEvent
        (String orderId,
         boolean payed){
}
