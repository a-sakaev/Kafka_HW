package com.example.service;

import com.example.dto.SentOrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentProducer {

    private static final Logger log = LoggerFactory.getLogger(SentProducer.class);


    private final KafkaTemplate<String, SentOrderEvent> kafkaTemplate;

    public void sendSentOrder(SentOrderEvent event){
        log.info("Отправка события в sent_orders: orderId={}, sent={}", event.orderId(), event.sent());
        kafkaTemplate.send("sent_orders", event.orderId(), event);
    }
}
