package com.learnmicrosrvice.orderservice.repo;

import com.learnmicrosrvice.orderservice.model.OrderLineItems;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LineItemrepo extends MongoRepository<OrderLineItems,String> {
    List<OrderLineItems> findByorderRef(String id);
}
