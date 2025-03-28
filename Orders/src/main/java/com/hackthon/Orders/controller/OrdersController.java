package com.hackthon.Orders.controller;

import com.hackthon.Orders.model.Orders;
import com.hackthon.Orders.service.OrderService;
import com.hackthon.Orders.util.OrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {
    Logger log = LoggerFactory.getLogger(OrdersController.class);
    @Autowired
    private OrderService orderService;

    @GetMapping("/hello")
    public String getHello() {
        log.info(OrderConstants.HELLO);
        return OrderConstants.HELLO;
    }

    @GetMapping("/api/orders")
    public List<Orders> getOrderDetails() {
        log.info(OrderConstants.FETCHING_ORDERS);
        List<Orders> orders = orderService.Getorderdetails();
        log.info(OrderConstants.TOTAL_ORDERS_FETCHED, orders.size());
        return orderService.Getorderdetails();
    }

    @PostMapping("/api/orders/create")
    public ResponseEntity<String> createOrder(@RequestBody Orders orders) {
        log.info(OrderConstants.ORDER_RECEIVED, orders);
        String response = orderService.createOrder(orders);
        log.info(OrderConstants.ORDER_CREATED_RESPONSE, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
