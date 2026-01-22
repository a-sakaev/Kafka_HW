package com.example.service;

import com.example.dto.SentOrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentProducer {

    private final KafkaTemplate<String, SentOrderEvent> kafkaTemplate;

    public void sendSentOrder(SentOrderEvent event){
        kafkaTemplate.send("sent_orders", event.orderId(), event);
    }
}
