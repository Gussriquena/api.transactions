package com.api.transactions.controller;

import com.api.transactions.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.api.transactions.util.MockProduct.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private static final String URL_PRODUCT = "/api/v1/product";

    @Test
    void when_insertProduct_returnSuccess() throws Exception {
        when(productService.insertProduct(any())).thenReturn(mockProduct());

        mockMvc.perform(post(URL_PRODUCT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockProduct())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockProduct())));

        verify(productService, times(1)).insertProduct(any());
    }

    @Test
    void when_insertProduct_returnBadRequest() throws Exception {
        mockMvc.perform(post(URL_PRODUCT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockNullProduct())))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Product name can't be null"));

        verify(productService, times(0)).insertProduct(any());
    }

    @Test
    void when_listProducts_returnSuccess() throws Exception {
        when(productService.listProducts()).thenReturn(List.of(mockProduct()));

        mockMvc.perform(get(URL_PRODUCT))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(mockProduct()))));

        verify(productService, times(1)).listProducts();
    }
}