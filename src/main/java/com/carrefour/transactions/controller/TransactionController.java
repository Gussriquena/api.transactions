package com.carrefour.transactions.controller;

import com.carrefour.transactions.domain.dto.TransactionDTO;
import com.carrefour.transactions.domain.model.Transaction;
import com.carrefour.transactions.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @PostMapping
    public Transaction insertTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new generic transaction with type ID: {}", transactionDTO.getIdTransactionType());
        return transactionService.insertTransaction(transactionDTO);
    }

    @PostMapping("/debit")
    public Transaction insertDebitTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new DEBIT transaction");
        transactionDTO.setIdTransactionType(DEBIT);
        return transactionService.insertTransaction(transactionDTO);
    }

    @PostMapping("/credit")
    public Transaction insertCreditTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new CREDIT transaction");
        transactionDTO.setIdTransactionType(CREDIT);
        return transactionService.insertTransaction(transactionDTO);
    }

}
