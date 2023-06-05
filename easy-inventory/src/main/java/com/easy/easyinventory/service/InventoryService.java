package com.easy.easyinventory.service;

import com.easy.easyinventory.model.dto.InventoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {
    public List<InventoryResponseDto>isInStock(List<String> skuCode);
}
