package com.easy.easyorder.controller;

import com.easy.easyorder.model.dto.OrderRequest;
import com.easy.easyorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
}
