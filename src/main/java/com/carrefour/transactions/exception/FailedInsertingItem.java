package com.carrefour.transactions.exception;

public class FailedInsertingItem extends RuntimeException {
    public FailedInsertingItem(String message){
        super(message);
    }
}
