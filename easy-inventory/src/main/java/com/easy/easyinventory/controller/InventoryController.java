package com.easy.easyinventory.controller;

import com.easy.easyinventory.model.dto.InventoryResponseDto;
import com.easy.easyinventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/isInStock")
    public List<InventoryResponseDto> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
