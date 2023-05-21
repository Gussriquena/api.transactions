package com.carrefour.transactions.util;

import com.carrefour.transactions.domain.model.TransactionType;

public class MockTransactionType {
    public static TransactionType mockDebitTransactionType(){
        return TransactionType.builder()
                .idTransaction(1L)
                .name("debit")
                .build();
    }

    public static TransactionType mockCreditTransactionType(){
        return TransactionType.builder()
                .idTransaction(2L)
                .name("credit")
                .build();
    }
}
