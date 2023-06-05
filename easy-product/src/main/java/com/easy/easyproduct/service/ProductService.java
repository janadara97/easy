package com.easy.easyproduct.service;

import com.easy.easyproduct.model.dto.ProductRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ResponseEntity<Object> createProduct (ProductRequestDto productRequest);
    public ResponseEntity<Object> getAllProducts();
}
