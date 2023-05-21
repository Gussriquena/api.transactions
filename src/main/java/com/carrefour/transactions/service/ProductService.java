package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.exception.FailedInsertingItem;
import com.carrefour.transactions.exception.ItemNotFoundException;
import com.carrefour.transactions.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
