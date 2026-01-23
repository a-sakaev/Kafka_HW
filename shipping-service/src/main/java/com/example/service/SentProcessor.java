package com.example.service;

import com.example.dto.PayedOrderEvent;
import com.example.dto.SentOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SentProcessor {

    private static final Logger log = LoggerFactory.getLogger(SentProcessor.class);

    public SentOrderEvent sent(PayedOrderEvent event){
        log.debug("Формирование события отправки в sent_orders: orderId={}, payed={}", event.orderId(), event.payed());
        return new SentOrderEvent(event.orderId(), true);
    }

}
