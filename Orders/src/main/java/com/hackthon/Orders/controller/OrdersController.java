package com.hackthon.Orders.controller;

import com.hackthon.Orders.DTO.Products;
import com.hackthon.Orders.model.Orders;
import com.hackthon.Orders.service.OrderService;
import com.hackthon.Orders.util.OrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class OrdersController {
    Logger log = LoggerFactory.getLogger(OrdersController.class);
    @Autowired
    private OrderService orderService;


    @Autowired
    private EmailClient emailClient;

    @GetMapping("/api/orders/{id}")
    public ResponseEntity<Orders> getOrderDetailsByOrderId(@PathVariable String id) {
        log.info("Fetching order with ID: {}", id);
        Orders order = orderService.getOrderById(id);
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/api/orders/user/{userId}")
    public ResponseEntity<List<Orders>> getOrderDetailsByUserID(@PathVariable String userId) {
        log.info(OrderConstants.FETCHING_ORDERS);
        List<Orders> orders = orderService.Getorderdetails();
        log.info(OrderConstants.TOTAL_ORDERS_FETCHED, orders.size());
        return new ResponseEntity<>(orderService.GetorderdetailsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/api/orders")
    public ResponseEntity<List<Orders>> getOrderDetails() {
        log.info(OrderConstants.FETCHING_ORDERS);
        List<Orders> orders = orderService.Getorderdetails();
        log.info(OrderConstants.TOTAL_ORDERS_FETCHED, orders.size());
//        placeOrder();
        return new ResponseEntity<>(orderService.Getorderdetails(), HttpStatus.OK);
    }

    @PostMapping("/api/orders/create")
    public ResponseEntity<String> createOrder(@RequestBody Orders orders) {
        log.info(OrderConstants.ORDER_RECEIVED, orders);
        String response = orderService.createOrder(orders);
        log.info(OrderConstants.ORDER_CREATED_RESPONSE, response);
//        placeOrder(orders);
        emailClient.sendOrderNotification(orders);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Orders> createProduct(@RequestBody Products product) {

        // Save the product


        return ResponseEntity.ok(orderService.createOrderByProductDetails(product));
    }
//    public String placeOrder() {
//        emailClient.sendEmailOrder();
//
//        return "Order placed successfully! Confirmation email sent.";
//    }
}
