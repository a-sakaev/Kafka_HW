package com.example.controller;

import com.example.dto.NewOrderEvent;
import com.example.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    public record CreateOrderRequest(String userId, double amount){}

    @PostMapping
    public NewOrderEvent create(@RequestBody CreateOrderRequest request){
        NewOrderEvent event = new NewOrderEvent(UUID.randomUUID().toString(), request.userId(), request.amount());
        producer.sendNewOrder(event);
        return event;
    }

}
