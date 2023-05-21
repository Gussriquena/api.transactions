package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.repository.ProductRepository;
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
        return productRepository.save(product);
    }

    public List<Product> listProducts() {
        List<Product> products = productRepository.findAll();

        log.info("{} products found", products.size());
        return products;
    }
}
