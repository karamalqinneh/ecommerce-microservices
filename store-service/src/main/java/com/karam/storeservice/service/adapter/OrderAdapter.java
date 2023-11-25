package com.karam.storeservice.service.adapter;


import com.karam.storeservice.domain.Order;
import com.karam.storeservice.web.dto.OrderDto;

public class OrderAdapter {

    public static OrderDto getDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getName(),
                order.getEmail(),
                order.getPhone(),
                order.getStreet(),
                order.getCity(),
                order.getZip(),
                order.getCreditCardType().toString(),
                order.getCcNumber(),
                order.getDateValid(),
                order.getCcv(),
                order.getStatus().toString(),
                order.getItems()
        );
    }

    public static Order getOrder(OrderDto order) {
        return new Order(
                order.getOrderId(),
                order.getName(),
                order.getEmail(),
                order.getPhone(),
                order.getStreet(),
                order.getCity(),
                order.getZip(),
                Order.CreditCard.fromString(order.getCreditCardType()),
                order.getCcNumber(),
                order.getDateValid(),
                order.getCcv(),
                Order.OrderStatus.fromString(order.getStatus()),
                order.getItems()
        );
    }
}
