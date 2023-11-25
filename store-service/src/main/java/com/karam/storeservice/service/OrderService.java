package com.karam.storeservice.service;


import com.karam.storeservice.data.OrderRepository;
import com.karam.storeservice.domain.Order;
import com.karam.storeservice.domain.ProductCartItem;
import com.karam.storeservice.service.adapter.OrderAdapter;
import com.karam.storeservice.web.dto.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;
    private OrderNotificationService notificationService;

    public Page<Order> findAll(Pageable paging) {
        return orderRepository.findAll(paging);
    }

    public OrderDto getSingleOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderAdapter.getDto(order);
    }

    public void addOrder(OrderDto orderDto) {
        Order order = OrderAdapter.getOrder(orderDto);
        List<ProductCartItem> items =orderDto.getItems();
        items.forEach(item -> {
            productService.updateStock(item.getProductId(), item.getCount());
        });

        order = orderRepository.save(order);
        notificationService.sendOrder(order);
    }

    public OrderDto updateOrderStatue(String orderId, String newValue) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(Order.OrderStatus.fromString(newValue));
        orderRepository.save(order);
        return OrderAdapter.getDto(order);
    }

    public List<ProductCartItem> getProductsInOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getItems();
    }
}
