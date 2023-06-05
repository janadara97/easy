package com.easy.easyinventory.service;

import com.easy.easyinventory.model.dto.InventoryResponseDto;
import com.easy.easyinventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    InventoryRepository inventoryRepository;
    @Override
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findAllBySkuCodeIn(skuCode).stream().map(item -> InventoryResponseDto.builder().skuCode(item.skuCode).isInStock(item.getQuantity() > 0 ? true : false).build()).collect(Collectors.toList());
    }
}
