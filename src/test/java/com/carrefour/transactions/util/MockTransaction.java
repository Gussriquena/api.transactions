package com.carrefour.transactions.util;

import com.carrefour.transactions.domain.dto.TransactionDTO;
import com.carrefour.transactions.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.carrefour.transactions.util.MockProduct.mockProduct;
import static com.carrefour.transactions.util.MockTransactionType.mockCreditTransactionType;
import static com.carrefour.transactions.util.MockTransactionType.mockDebitTransactionType;

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
                .transactionType(mockDebitTransactionType())
                .product(mockProduct())
                .price(BigDecimal.TEN)
                .amount(10)
                .build();
    }

    public static Transaction mockCreditTransaction(){
        return Transaction.builder()
                .idTransaction(2L)
                .date(LocalDate.of(2023, 5, 21))
                .transactionType(mockCreditTransactionType())
                .product(mockProduct())
                .price(BigDecimal.TEN)
                .amount(10)
                .build();
    }

}
