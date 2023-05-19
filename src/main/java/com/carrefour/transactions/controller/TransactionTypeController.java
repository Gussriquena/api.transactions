package com.carrefour.transactions.controller;

import com.carrefour.transactions.domain.model.TransactionType;
import com.carrefour.transactions.service.TransactionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/transaction-type")
@AllArgsConstructor
public class TransactionTypeController {

    @Autowired
    private final TransactionTypeService transactionTypeService;

    @GetMapping
    public List<TransactionType> listTransactionTypes(){
        return transactionTypeService.listTrasactionTypes();
    }

}
