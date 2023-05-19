package com.carrefour.transactions.controller;

import com.carrefour.transactions.domain.dto.TransactionDTO;
import com.carrefour.transactions.domain.model.Transaction;
import com.carrefour.transactions.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.carrefour.transactions.util.Constants.CREDIT;
import static com.carrefour.transactions.util.Constants.DEBIT;

@RestController
@RequestMapping("api/v1/transaction")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @PostMapping
    public Transaction insertTransaction(@RequestBody TransactionDTO transactionDTO){
        return transactionService.insertDebitTransaction(transactionDTO);
    }

    @PostMapping("/debit")
    public Transaction insertDebitTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionDTO.setIdTransactionType(DEBIT);
        return transactionService.insertDebitTransaction(transactionDTO);
    }

    @PostMapping("/credit")
    public Transaction insertCreditTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionDTO.setIdTransactionType(CREDIT);
        return transactionService.insertDebitTransaction(transactionDTO);
    }

}
