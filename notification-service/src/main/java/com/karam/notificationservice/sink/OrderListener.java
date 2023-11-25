package com.karam.notificationservice.sink;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karam.notificationservice.domain.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private ObjectMapper objectMapper;

    public OrderListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics="orders.topic")
    public void handle(String order) throws JsonProcessingException {
        Order serial = objectMapper.readValue(order, Order.class);
        System.out.println("####################### NEW ORDER ##############################");
        System.out.println(serial);
        System.out.println("#####################################################");
    }
}