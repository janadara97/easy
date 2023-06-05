package com.easy.easyproduct.service;

import com.easy.easyproduct.model.Product;
import com.easy.easyproduct.model.dto.ApiResponse;
import com.easy.easyproduct.model.dto.ProductRequestDto;
import com.easy.easyproduct.model.dto.ProductResponseDto;
import com.easy.easyproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<Object> createProduct(ProductRequestDto productRequest) {
        try {
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().status(true).message("Product Created Successfully").build());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder().status(false).message("Failed to create the product").build());
        }
    }

    @Override
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<ProductResponseDto> products = productRepository.findAll().stream().map(e->ProductResponseDto.builder().name(e.getName()).description(e.getDescription()).id(e.getId()).price(e.getPrice()).build()).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().status(true).data(products).build());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder().status(false).message("Products Details currently Unavailable").build());
        }
    }
}
