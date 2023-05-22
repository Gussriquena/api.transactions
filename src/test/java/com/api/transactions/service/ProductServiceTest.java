package com.api.transactions.service;

import com.api.transactions.domain.model.Product;
import com.api.transactions.exception.FailedInsertingItem;
import com.api.transactions.exception.ItemNotFoundException;
import com.api.transactions.repository.ProductRepository;
import com.api.transactions.util.MockProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void when_insertProduct_returnSuccess() {
        when(productRepository.save(any())).thenReturn(MockProduct.mockProduct());

        Product product = productService.insertProduct(MockProduct.mockProduct());

        Assertions.assertEquals(MockProduct.mockProduct(), product);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void when_insertProduct_returnException() {
        when(productRepository.save(any())).thenThrow(new FailedInsertingItem(""));
        assertThrows(FailedInsertingItem.class, () -> productService.insertProduct(MockProduct.mockProduct()));
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void when_listProducts_returnSuccess() {
        when(productRepository.findAll()).thenReturn(List.of(MockProduct.mockProduct()));

        List<Product> products = productService.listProducts();

        Assertions.assertEquals(List.of(MockProduct.mockProduct()), products);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void when_insertProduct_throwsException() {
        when(productRepository.findAll()).thenThrow(new ItemNotFoundException(""));
        assertThrows(ItemNotFoundException.class, () -> productService.listProducts());
        verify(productRepository, times(1)).findAll();
    }
}