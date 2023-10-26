package com.learnmicrosrvice.inventoryservice.service;

import com.learnmicrosrvice.inventoryservice.dto.InventoryDto;
import com.learnmicrosrvice.inventoryservice.model.Inventory;
import com.learnmicrosrvice.inventoryservice.repo.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepo repo;
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode, Integer quantity){
        Optional<Inventory> inventory =repo.findBySkuCode(skuCode);
        if(inventory.isPresent() && inventory.get().getQuantity()>=quantity){
            return true;
        }
        return false;
    }

    public void createInventory(InventoryDto request) {
        Inventory inventory = Inventory.builder()
                .skuCode(request.getSkuCode())
                .quantity(request.getQuantity())
                .comment(request.getComment())
                .build();
        repo.save(inventory);
    }
}
