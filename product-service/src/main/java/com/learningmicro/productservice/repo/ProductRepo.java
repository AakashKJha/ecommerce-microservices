package com.learningmicro.productservice.repo;

import com.learningmicro.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product,String> {
    List<Product> findByName(String test);
}
