package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.exception.FailedInsertingItem;
import com.carrefour.transactions.exception.ItemNotFoundException;
import com.carrefour.transactions.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.carrefour.transactions.util.MockProduct.mockProduct;
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
        when(productRepository.save(any())).thenReturn(mockProduct());

        Product product = productService.insertProduct(mockProduct());

        assertEquals(mockProduct(), product);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void when_insertProduct_returnException() {
        when(productRepository.save(any())).thenThrow(new FailedInsertingItem(""));
        assertThrows(FailedInsertingItem.class, () -> productService.insertProduct(mockProduct()));
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void when_listProducts_returnSuccess() {
        when(productRepository.findAll()).thenReturn(List.of(mockProduct()));

        List<Product> products = productService.listProducts();

        assertEquals(List.of(mockProduct()), products);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void when_insertProduct_throwsException() {
        when(productRepository.findAll()).thenThrow(new ItemNotFoundException(""));
        assertThrows(ItemNotFoundException.class, () -> productService.listProducts());
        verify(productRepository, times(1)).findAll();
    }
}