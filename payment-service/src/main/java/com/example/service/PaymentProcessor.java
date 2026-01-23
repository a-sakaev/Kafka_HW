package com.example.service;

import com.example.dto.NewOrderEvent;
import com.example.dto.PayedOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class PaymentProcessor {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessor.class);

    public PayedOrderEvent pay(NewOrderEvent event){
        log.debug("Платёж обработан. orderId={}, amount={}", event.orderId(), event.amount());
        return new PayedOrderEvent(event.orderId(), true);
    }

}
