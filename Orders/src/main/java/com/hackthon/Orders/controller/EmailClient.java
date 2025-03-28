package com.hackthon.Orders.controller;

import com.hackthon.Orders.model.Orders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Notification", url = "http://localhost:8087/")
public interface EmailClient {

    @GetMapping("/emailorder")
    String sendEmailOrder();

    @PostMapping("/send")
    ResponseEntity<String> sendOrderNotification(@RequestBody Orders order);

}
