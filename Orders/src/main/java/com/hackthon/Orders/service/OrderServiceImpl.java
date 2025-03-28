package com.hackthon.Orders.service;

import com.hackthon.Orders.DTO.Products;
import com.hackthon.Orders.Exception.ExceptionDetails;
import com.hackthon.Orders.model.Orders;
import com.hackthon.Orders.repositery.OrdersRepositery;
import com.hackthon.Orders.util.OrderConstants;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrdersRepositery ordersRepository;

    @Override
    public List<Orders> Getorderdetails() {
        logger.info(OrderConstants.FETCHING_ORDERS);
        List<Orders> orders = ordersRepository.findAll();
        logger.info(OrderConstants.TOTAL_ORDERS_FETCHED, orders.size());
        return orders;
    }

    @Override
    public List<Orders> GetorderdetailsByUserId(String orderId) {
        logger.info(OrderConstants.FETCHING_ORDERS);
        List<Orders> orders = ordersRepository.findByUserId(orderId);
        logger.info(OrderConstants.TOTAL_ORDERS_FETCHED, orders.size());
        return orders;
    }

    @Override
    @Transactional

    public Orders createOrderByProductDetails(Products products) {
        Orders orders = new Orders("101012", "", products.getPrice(), "Passed", LocalDateTime.now());
        createOrder(orders);
        Orders savedOrder = ordersRepository.save(orders); // Save and return saved instance
        logger.info(OrderConstants.ORDER_CREATED + savedOrder.getOrderId());

        return savedOrder; // ✅ Return the saved order
    }


    @Override
    public String createOrder(Orders orders) {
        logger.info(OrderConstants.RECEIVED_REQUEST, orders);

        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        orders.setOrderId(orderId);
        // Save order
        Orders lastOrder = ordersRepository.save(orders);
        logger.info(OrderConstants.ORDER_CREATED + lastOrder.getOrderId());

        return OrderConstants.ORDER_CREATED + lastOrder.getOrderId();
    }

    @Override
    public Orders getOrderById(String id) {
        Optional<Orders> order = ordersRepository.findById(id);
        return order.orElseThrow(() -> new ExceptionDetails(OrderConstants.ORDER_NOT_WITH_ID + id));
    }

}
