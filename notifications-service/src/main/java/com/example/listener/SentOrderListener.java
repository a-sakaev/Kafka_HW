package com.example.listener;

import com.example.dto.SentOrderEvent;
import com.example.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SentOrderListener {

    private final NotificationService service;

    @KafkaListener(topics = "${app.kafka.topics.sent-orders}",
    concurrency = "${app.kafka.listener.concurrency:3}")
    public void handle(SentOrderEvent event){
        if (event.sent()){
            service.notificationDelivered(event.orderId());
        }
    }

}
