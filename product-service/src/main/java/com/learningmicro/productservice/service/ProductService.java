package com.learningmicro.productservice.service;

import com.learningmicro.productservice.dto.ProductRequest;
import com.learningmicro.productservice.dto.ProductResponse;
import com.learningmicro.productservice.model.Product;
import com.learningmicro.productservice.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;


    public void createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        productRepo.save(product);
        log.info("Product {} created successfully",product.getId());
    }

    public ProductResponse getProduct(String id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public List<ProductResponse> getAllProduct() {
        return productRepo.findAll().stream().map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build()).toList();
    }
}
