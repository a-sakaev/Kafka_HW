package com.example.service;

import com.example.dto.NewOrderEvent;
import com.example.dto.PayedOrderEvent;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessor {

    public PayedOrderEvent pay(NewOrderEvent event){
        return new PayedOrderEvent(event.orderId(), true);
    }

}
