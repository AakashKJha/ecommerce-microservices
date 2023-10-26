package com.learnmicrosrvice.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InventoryDto {
    private String skuCode;
    private String comment;
    private Integer quantity;
    private Integer block;
}
