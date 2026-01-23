package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void notificationDelivered(String orderId){

    log.info("Уведомление доставлено, ID заказа={}", orderId);

    }

}
