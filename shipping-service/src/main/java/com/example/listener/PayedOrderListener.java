package com.example.listener;

import com.example.dto.PayedOrderEvent;
import com.example.dto.SentOrderEvent;
import com.example.service.SentProcessor;
import com.example.service.SentProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayedOrderListener {

    private final SentProcessor processor;
    private final SentProducer producer;
    private static final Logger log = LoggerFactory.getLogger(PayedOrderListener.class);

    @KafkaListener(topics = "payed_orders", groupId = "shipping-service")
    public void handle(PayedOrderEvent event,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset){

        log.info("Получен заказ и payed_orders [partition={}, offset={}, orderId={}, payed={}]",
                partition, offset, event.orderId(), event.payed());

        SentOrderEvent out = processor.sent(event);

        log.info("Заказ отправлен в доставку: orderId={}, sent={}",
                out.orderId(), out.sent());

        producer.sendSentOrder(out);
        log.info("Событие отправлено в sent_orders: orderId={}", out.orderId());
    }

}
