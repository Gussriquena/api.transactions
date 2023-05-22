package com.api.transactions.util;

import com.api.transactions.domain.dto.TransactionDTO;
import com.api.transactions.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.api.transactions.util.MockProduct.mockProduct;

public class MockTransaction {

    public static TransactionDTO mockTransactionDTO(){
        return TransactionDTO.builder()
                .idProduct(1L)
                .price(BigDecimal.TEN)
                .transactionAmount(10)
                .build();
    }

    public static TransactionDTO mockDebitTransactionDTO(){
        return TransactionDTO.builder()
                .idTransactionType(1L)
                .idProduct(1L)
                .price(BigDecimal.TEN)
                .transactionAmount(10)
                .build();
    }

    public static Transaction mockDebitTransaction(){
        return Transaction.builder()
                .idTransaction(1L)
                .date(LocalDate.of(2023, 5, 21))
                .transactionType(MockTransactionType.mockDebitTransactionType())
                .product(mockProduct())
                .price(BigDecimal.TEN)
                .amount(10)
                .build();
    }

    public static Transaction mockCreditTransaction(){
        return Transaction.builder()
                .idTransaction(2L)
                .date(LocalDate.of(2023, 5, 21))
                .transactionType(MockTransactionType.mockCreditTransactionType())
                .product(mockProduct())
                .price(BigDecimal.TEN)
                .amount(10)
                .build();
    }

}
