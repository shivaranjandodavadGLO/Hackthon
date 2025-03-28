package com.hackthon.Orders.service;

import com.hackthon.Orders.Exception.ExceptionDetails;
import com.hackthon.Orders.model.Orders;
import com.hackthon.Orders.repositery.OrdersRepositery;
import com.hackthon.Orders.util.OrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public String createOrder(Orders orders) {
        logger.info(OrderConstants.RECEIVED_REQUEST, orders);

        // Check if the order object is null
        if (orders == null) {
            logger.error(OrderConstants.ORDER_CANNOT_NULL);
            throw new ExceptionDetails(OrderConstants.ORDER_CANNOT_NULL);
        }

        // Check if the order ID already exists
        if (orders.getOrderId() != null && ordersRepository.existsById(orders.getOrderId())) {
            logger.warn(OrderConstants.ORDER_WITH_ID + orders.getOrderId() + OrderConstants.ALLREADY_EXIST);
            throw new ExceptionDetails(OrderConstants.ORDER_WITH_ID + orders.getOrderId() + OrderConstants.ALLREADY_EXIST);
        }

        // Validate required fields
        if (orders.getTotalAmount() == null || orders.getTotalAmount() <= 0) {
            logger.error(OrderConstants.PRICE_GREATERTHAN_ZERO);
            throw new ExceptionDetails(OrderConstants.PRICE_GREATERTHAN_ZERO);
        }
        if (orders.getUserId() == null) {
            logger.error(OrderConstants.USER_ID_CANNOT_NULL);
            throw new ExceptionDetails(OrderConstants.USER_ID_CANNOT_NULL);
        }

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
