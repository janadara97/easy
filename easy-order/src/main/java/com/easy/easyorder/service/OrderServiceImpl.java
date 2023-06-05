package com.easy.easyorder.service;

import com.easy.easyorder.model.Order;
import com.easy.easyorder.model.OrderLineItem;
import com.easy.easyorder.model.dto.InventoryResponseDto;
import com.easy.easyorder.model.dto.OrderRequest;
import com.easy.easyorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    WebClient webClient;
    @Override
    public ResponseEntity<Object> createOrder(OrderRequest orderRequest) {
        try{
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItems().stream().map(item-> OrderLineItem.builder()
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .skuCode(item.getSkuCode())
                    .build()).collect(Collectors.toList());
            order.setOrderLineItems(orderLineItemList);
            List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItem::getSkuCode).collect(Collectors.toList());
            //check the availability of the stock by calling to the inventory service
            InventoryResponseDto[] inventoryResponse = webClient.get()
                            .uri("http://localhost:8082/api/inventory/isInStock",
                                    uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                                    .retrieve()
                                            .bodyToMono(InventoryResponseDto[].class)
                                                    .block();
            boolean allProductsInStock = false;
            if(inventoryResponse != null && inventoryResponse.length > 0){
                allProductsInStock = Arrays.stream(inventoryResponse).allMatch(InventoryResponseDto::getIsInStock);
            }
            if(Boolean.TRUE.equals(allProductsInStock)) {
                orderRepository.save(order);
            }
            else {
                throw new IllegalArgumentException("Product is not in stock");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Order created Successfully");
        }catch(Exception e){
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }
}
