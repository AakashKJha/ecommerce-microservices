package com.learningmicro.productservice.controller;

import com.learningmicro.productservice.dto.ProductRequest;
import com.learningmicro.productservice.dto.ProductResponse;
import com.learningmicro.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request){
                productService.createProduct(request);
    }
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }
    @GetMapping("/")
    public List<ProductResponse>getAllProduct(){
        return productService.getAllProduct();
    }
}
