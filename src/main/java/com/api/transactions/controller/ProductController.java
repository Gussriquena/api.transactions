package com.api.transactions.controller;

import com.api.transactions.domain.model.Product;
import com.api.transactions.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path="api/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestBody Product product){
        if(isNull(product.getName())){
            log.error("Found null fields on requestBody");
            return ResponseEntity.badRequest().body("Product name can't be null");
        }

        log.info("Inserting product: {}", product.getName());
        return ResponseEntity.ok().body(productService.insertProduct(product));
    }

    @GetMapping
    public ResponseEntity<?> listProducts(){
        log.info("Listing all products");
        return ResponseEntity.ok().body(productService.listProducts());
    }

}
