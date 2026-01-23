package com.example.service;

import com.example.dto.NewOrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, NewOrderEvent> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    public void sendNewOrder(NewOrderEvent event){

        kafkaTemplate.send("new_orders", event.orderId(), event);
        log.info("Создание нового заказа ID={}",
                event.orderId());
    }

}
