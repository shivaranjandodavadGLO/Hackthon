package com.hackathon.Products.controller;

import com.hackathon.Products.model.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@FeignClient(name = "order-service", url = "http://localhost:8085") // Update the URL if using service discovery
public interface OrderServiceFeignClient {

    @PostMapping("/addProduct")
    ResponseEntity<String> sendProductToOrderService(@RequestBody Products product);
}
