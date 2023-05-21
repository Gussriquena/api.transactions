package com.carrefour.transactions.controller;

import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    public Product insertProduct(@RequestBody Product product){
        log.info("Inserting product: {}", product.getName());
        return productService.insertProduct(product);
    }

    @GetMapping
    public Object listProducts(){
        log.info("Listing all products");
        return productService.listProducts();
    }

}
