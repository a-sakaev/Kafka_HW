package com.example.service;

import com.example.dto.PayedOrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, PayedOrderEvent> kafkaTemplate;

    public void sendPayedOrder(PayedOrderEvent event){
        kafkaTemplate.send("payed_orders", event.orderId(), event);
    }
}
