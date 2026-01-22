package com.example.listener;

import com.example.dto.PayedOrderEvent;
import com.example.dto.SentOrderEvent;
import com.example.service.SentProcessor;
import com.example.service.SentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayedOrderListener {

    private final SentProcessor processor;
    private final SentProducer producer;

    @KafkaListener(topics = "payed_orders", groupId = "shipping-service")
    public void handle(PayedOrderEvent event){
        SentOrderEvent out = processor.sent(event);
        producer.sendSentOrder(out);
    }

}
