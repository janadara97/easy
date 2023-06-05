package com.easy.easyinventory.repository;

import com.easy.easyinventory.model.Inventory;
import com.easy.easyinventory.model.dto.InventoryResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    public List<Inventory> findAllBySkuCodeIn(List<String> skuCode);
}
