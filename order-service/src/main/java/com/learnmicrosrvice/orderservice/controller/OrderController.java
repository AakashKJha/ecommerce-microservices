package com.learnmicrosrvice.orderservice.controller;

import com.learnmicrosrvice.orderservice.dto.OrderRequest;
import com.learnmicrosrvice.orderservice.dto.OrderResponse;
import com.learnmicrosrvice.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackInventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(){
        return orderService.getOrders();
    }
    public String fallbackInventory(OrderRequest orderRequest, Exception e){
        return ("\nInventory Service is down, please try after some time");

    }
}
