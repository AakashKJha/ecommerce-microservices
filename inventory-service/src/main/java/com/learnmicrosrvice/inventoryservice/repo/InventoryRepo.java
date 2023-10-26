package com.learnmicrosrvice.inventoryservice.repo;

import com.learnmicrosrvice.inventoryservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InventoryRepo extends MongoRepository<Inventory,String> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
