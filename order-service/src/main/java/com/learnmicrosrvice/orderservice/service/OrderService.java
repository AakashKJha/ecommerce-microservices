package com.learnmicrosrvice.orderservice.service;

import com.learnmicrosrvice.orderservice.dto.OrderLineItemsDto;
import com.learnmicrosrvice.orderservice.dto.OrderRequest;
import com.learnmicrosrvice.orderservice.dto.OrderResponse;
import com.learnmicrosrvice.orderservice.model.Order;
import com.learnmicrosrvice.orderservice.model.OrderLineItems;
import com.learnmicrosrvice.orderservice.repo.LineItemrepo;
import com.learnmicrosrvice.orderservice.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final LineItemrepo lineItemrepo;
    private final OrderRepo orderRepo;
    private final WebClient webClient;

    public void placeOrder(OrderRequest request){
        Order order = new Order();
        order.setComment(request.getComment());
        double total = 0;
        for (OrderLineItemsDto order1 : request.getOrderLineItemsDtoList()){
            total+=(order1.getPrice()*order1.getQuantity());
            //check inventory
            log.info("calling inventory service");
            Boolean res=webClient.get()
                    .uri(uriBuilder -> uriBuilder

                            .scheme("http")
                            .host("localhost")
                            .port(8082)
                            .path("/api/inventory/{skucode}/{quantity}")
                            .build(order1.getSkuCode(), order1.getQuantity()))
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if(!res){
                throw new IllegalArgumentException("Product is not in stock");
            }
        }
        order.setTotalPrice(total);
        orderRepo.save(order);

        log.info("calling inventory service returned true");

        List<OrderLineItems> lineItemsList=request.getOrderLineItemsDtoList()
                .stream()
                .map(item-> OrderLineItems.builder()
                                .price(item.getPrice())
                                .skuCode(item.getSkuCode())
                                .quantity(item.getQuantity())
                                .orderRef(order.getId())
                                .build()
                        ).toList();
        lineItemrepo.saveAll(lineItemsList);


    }

    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> responses = new ArrayList<>();
        for (Order order: orders ){
            List<OrderLineItems> lineItems = lineItemrepo.findByorderRef(order.getId());
            OrderResponse response = OrderResponse.builder()
                    .id(order.getId())
                    .orderLineItems(lineItems)
                    .comment(order.getComment())
                    .totalPrice(order.getTotalPrice())
                    .build();
            responses.add(response);

        }
        return responses;

    }
}
