package com.learnmicrosrvice.orderservice.repo;

import com.learnmicrosrvice.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order,String> {
}
