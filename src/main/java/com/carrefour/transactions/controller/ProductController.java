package com.carrefour.transactions.controller;

import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    public Object insertProduct(@RequestBody Product product){
        return productService.insertProduct(product);
    }

}
