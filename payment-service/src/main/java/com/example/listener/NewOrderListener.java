package com.example.listener;

import com.example.dto.NewOrderEvent;
import com.example.dto.PayedOrderEvent;
import com.example.service.PaymentProcessor;
import com.example.service.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewOrderListener {

    private static final Logger log = LoggerFactory.getLogger(NewOrderListener.class);
    private final PaymentProcessor processor;
    private final PaymentProducer producer;

    @KafkaListener(topics = "new_orders", groupId = "payment-service")
    public void handle(NewOrderEvent event,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset){

        log.info("Получен заказ и new_orders [partition={}, offset={}, orderId={}, userId={}, amount={}]",
                partition, offset, event.orderId(), event.userId(), event.amount());

        PayedOrderEvent out = processor.pay(event);

        log.info("Платеж обработан: orderId={}, payed={}",
                out.orderId(), out.payed());

        producer.sendPayedOrder(out);
        log.info("Событие об оплате отправлено в sent_orders: orderId={}", out.orderId());
    }

}
