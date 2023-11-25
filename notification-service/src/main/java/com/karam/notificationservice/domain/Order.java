package com.karam.notificationservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String name;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String zip;
    private CreditCard creditCardType;
    private String ccNumber;
    private LocalDate dateValid;
    private int ccv;
    private OrderStatus status;
    private List<Object> items;

    public enum CreditCard {
        MASTER_CARD, VISA;

        public static CreditCard fromString(String str) {
            if (str.equals("visa")) return VISA;
            if (str.equals("master card")) return MASTER_CARD;
            return VISA;
        }
    }

    public enum OrderStatus {
        PLACED, SHIPPED, DELIVIRED;

        public static OrderStatus fromString(String str) {
            return switch (str) {
                case "shipped" -> SHIPPED;
                case "delivered" -> DELIVIRED;
                default -> PLACED;
            };
        }
    }
}
