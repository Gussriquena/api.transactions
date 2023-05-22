package com.api.transactions.util;

import com.api.transactions.domain.model.Product;

public class MockProduct {

    public static Product mockNullProduct(){
        return Product.builder()
                .amount(10)
                .build();
    }

    public static Product mockProduct(){
        return Product.builder()
                .idProduct(1L)
                .amount(10)
                .name("Carregador")
                .build();
    }

}
