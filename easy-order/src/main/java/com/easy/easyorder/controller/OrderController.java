package com.easy.easyorder.controller;

import com.easy.easyorder.model.dto.OrderRequest;
import com.easy.easyorder.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    @CircuitBreaker(name = "inventory" , fallbackMethod = "fallBackMethod")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    public ResponseEntity<Object> fallBackMethod(OrderRequest orderRequest,RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
    }
}
