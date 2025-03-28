package com.hackthon.Orders.service;

import com.hackthon.Orders.DTO.Products;
import com.hackthon.Orders.model.Orders;

import java.util.List;


public interface OrderService {
    List<Orders> Getorderdetails();

    String createOrder(Orders orders);


    Orders getOrderById(String id);

    List<Orders> GetorderdetailsByUserId(String id);

    Orders createOrderByProductDetails(Products products);
}
