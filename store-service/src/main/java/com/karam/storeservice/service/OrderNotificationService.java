package com.karam.storeservice.service;

import com.karam.storeservice.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderNotificationService {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderNotificationService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        kafkaTemplate.send("orders.topic", order);
    }
}