package com.hackthon.Orders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }
}
