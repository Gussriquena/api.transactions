package com.api.transactions.exception;

public class FailedInsertingItem extends RuntimeException {
    public FailedInsertingItem(String message){
        super(message);
    }
}
