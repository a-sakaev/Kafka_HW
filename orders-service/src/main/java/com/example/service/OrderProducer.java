package com.example.service;

import com.example.dto.NewOrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, NewOrderEvent> kafkaTemplate;

    public void sendNewOrder(NewOrderEvent event){
        kafkaTemplate.send("new_orders", event.orderId(), event);
    }

}
