package com.api.transactions.service;

import com.api.transactions.domain.model.Product;
import com.api.transactions.exception.FailedInsertingItem;
import com.api.transactions.exception.ItemNotFoundException;
import com.api.transactions.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public Product insertProduct(Product product){
        try {
            return productRepository.save(product);
        } catch(Exception e){
            throw new FailedInsertingItem(e.getMessage());
        }
    }

    public List<Product> listProducts() {
        try {
            List<Product> products = productRepository.findAll();
            log.info("{} products found", products.size());
            return products;
        } catch(Exception e){
            throw new ItemNotFoundException(e.getMessage());
        }
    }
}
