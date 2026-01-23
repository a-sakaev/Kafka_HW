package com.example.service;

import com.example.dto.PayedOrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, PayedOrderEvent> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(PaymentProducer.class);

    public void sendPayedOrder(PayedOrderEvent event){
        log.info("Отправка события в payed_orders. orderId={}, payed={}",
                event.orderId(), event.payed());
        kafkaTemplate.send("payed_orders", event.orderId(), event);
    }
}
