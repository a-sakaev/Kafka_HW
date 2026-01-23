package com.example.listener;

import com.example.dto.SentOrderEvent;
import com.example.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class SentOrderListener {

    private static final Logger log = LoggerFactory.getLogger(SentOrderListener.class);
    private final NotificationService service;

    @KafkaListener(topics = "${app.kafka.topics.sent-orders}",
    concurrency = "${app.kafka.listener.concurrency:3}")
    public void handle(SentOrderEvent event,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset){
        log.info("Обработка отправки заказа [partition={}, offset={}, orderId={},sent={}]",
                partition, offset, event.orderId(), event.sent());
        if (event.sent()){
            service.notificationDelivered(event.orderId());
        }else{
            log.warn("Заказ еще не отправлен. ID заказа={}", event.orderId());
        }
    }

}
