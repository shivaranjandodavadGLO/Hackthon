package com.hackthon.Orders.service;

import com.hackthon.Orders.model.Orders;

import java.util.List;


public interface OrderService {
    List<Orders> Getorderdetails();

    String createOrder(Orders orders);


}
