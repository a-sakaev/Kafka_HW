package com.example.service;

import com.example.dto.SentOrderEvent;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notificationDelivered(String orderId){

        System.out.println("Уведомление отправлено: " + orderId);

    }

}
