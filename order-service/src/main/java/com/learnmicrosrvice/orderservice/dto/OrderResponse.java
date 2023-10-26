package com.learnmicrosrvice.orderservice.dto;

import com.learnmicrosrvice.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private String comment;
    private double totalPrice;
    private List<OrderLineItems> orderLineItems;
}
