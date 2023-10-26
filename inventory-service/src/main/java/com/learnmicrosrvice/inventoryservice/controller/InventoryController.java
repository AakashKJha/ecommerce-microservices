package com.learnmicrosrvice.inventoryservice.controller;

import com.learnmicrosrvice.inventoryservice.dto.InventoryDto;
import com.learnmicrosrvice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;
    @GetMapping("/{skucode}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("skucode") String skuCode,
                             @PathVariable("quantity") Integer quantity){
        return service.isInStock(skuCode,quantity);
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStock(@RequestBody InventoryDto request){
        service.createInventory(request);
    }

}
