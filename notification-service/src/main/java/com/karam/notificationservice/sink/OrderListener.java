package com.karam.notificationservice.sink;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karam.notificationservice.domain.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderListener {

    private ObjectMapper objectMapper;

    public OrderListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics="orders.topic")
    public void handle(String order) throws JsonProcessingException {
        log.debug("notification newOrder start");
        Order serial = objectMapper.readValue(order, Order.class);

        System.out.println(serial);
        System.out.println("#####################################################");
        log.debug("notification newOrder end");
    }
}