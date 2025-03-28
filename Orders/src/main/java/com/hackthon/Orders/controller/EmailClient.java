package com.hackthon.Orders.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Notification", url = "http://localhost:8087/")
public interface EmailClient {

    @GetMapping("/emailorder")
    String sendEmailOrder();
}
