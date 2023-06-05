package com.easy.easyorder.service;

import com.easy.easyorder.model.dto.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public ResponseEntity<Object> createOrder(OrderRequest orderRequest);
}
