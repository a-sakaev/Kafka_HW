package com.example.listener;

import com.example.dto.NewOrderEvent;
import com.example.dto.PayedOrderEvent;
import com.example.service.PaymentProcessor;
import com.example.service.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewOrderListener {

    private final PaymentProcessor processor;
    private final PaymentProducer producer;

    @KafkaListener(topics = "new_orders", groupId = "payment-service")
    public void handle(NewOrderEvent event){
        PayedOrderEvent out = processor.pay(event);
        producer.sendPayedOrder(out);
    }

}
