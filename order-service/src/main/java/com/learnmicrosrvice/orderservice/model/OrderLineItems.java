package com.learnmicrosrvice.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;


@Document(value = "t_order_line_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderLineItems {
    @Id
    private String id;
    private String orderRef;
    private String skuCode;
    private Integer quantity;
    private double price;
}
