package com.api.transactions.controller;

import com.api.transactions.service.TransactionTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transaction-type")
@AllArgsConstructor
@Slf4j
public class TransactionTypeController {

    @Autowired
    private final TransactionTypeService transactionTypeService;

    @GetMapping
    public ResponseEntity<?> listTransactionTypes(){
        log.info("Listing all transaction types");
        return ResponseEntity.ok().body(transactionTypeService.listTransactionTypes());
    }

}
