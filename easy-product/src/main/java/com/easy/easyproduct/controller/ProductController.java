package com.easy.easyproduct.controller;

import com.easy.easyproduct.model.dto.ProductRequestDto;
import com.easy.easyproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/create" )
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequestDto productRequest){
        return productService.createProduct(productRequest);
    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> getAllProducts(){
        return productService.getAllProducts();
    }
}
