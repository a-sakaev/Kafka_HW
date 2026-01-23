package com.example.controller;

import com.example.dto.NewOrderEvent;
import com.example.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    public record CreateOrderRequest(String userId, double amount){}

    @PostMapping
    public NewOrderEvent create(@RequestBody CreateOrderRequest request){
        NewOrderEvent event = new NewOrderEvent(UUID.randomUUID().toString(), request.userId(), request.amount());
        log.info("HTTP создание заказа: userId={}, amount={}, orderId={}",
                request.userId(), request.amount(), event.orderId());
        producer.sendNewOrder(event);
        return event;
    }

}
