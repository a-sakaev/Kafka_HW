package com.example.service;

import com.example.dto.PayedOrderEvent;
import com.example.dto.SentOrderEvent;
import org.springframework.stereotype.Service;

@Service
public class SentProcessor {

    public SentOrderEvent sent(PayedOrderEvent event){
        return new SentOrderEvent(event.orderId(), true);
    }

}
