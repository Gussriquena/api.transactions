package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public Product insertProduct(Product product){
        return productRepository.save(product);
    }
}
