package com.karam.storeservice.web.controller;


import com.karam.storeservice.domain.Order;
import com.karam.storeservice.domain.ProductCartItem;
import com.karam.storeservice.service.OrderService;
import com.karam.storeservice.web.dto.OrderDto;
import com.karam.storeservice.web.util.PageResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Log4j2
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        log.debug("store getAll start");
        Pageable paging = PageRequest.of(page, size);
        Page<Order> query = orderService.findAll(paging);

        Map<String, Object> response = PageResponse.getPagedResponse(query);

        log.debug("store getAll end");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getSingleOrder(@PathVariable String orderId) {
        OrderDto order = orderService.getSingleOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{orderId}/product")
    public ResponseEntity<?> getOrderProducts(@PathVariable String orderId) {
        List<ProductCartItem> order = orderService.getProductsInOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNew(@Valid @RequestBody OrderDto orderDto) {
        log.debug("store addNew start");
        orderService.addOrder(orderDto);

        log.debug("store addNew end");
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String orderId, @RequestParam String newValue) {
        OrderDto order = orderService.updateOrderStatue(orderId, newValue);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}


