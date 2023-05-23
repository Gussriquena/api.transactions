package com.api.transactions.controller;

import com.api.transactions.domain.dto.TransactionDTO;
import com.api.transactions.util.Constants;
import com.api.transactions.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/transaction")
@AllArgsConstructor
@Slf4j
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Insert generic transaction (insert the type using the field idTransactionType)")
    public ResponseEntity<?> insertTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new generic transaction with type ID: {}", transactionDTO.getIdTransactionType());
        return ResponseEntity.ok().body(transactionService.insertTransaction(transactionDTO));
    }

    @PostMapping("/debit")
    @Operation(summary = "Insert Debit transaction")
    public ResponseEntity<?> insertDebitTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new DEBIT transaction");
        transactionDTO.setIdTransactionType(Constants.DEBIT);
        return ResponseEntity.ok().body(transactionService.insertTransaction(transactionDTO));
    }

    @PostMapping("/credit")
    @Operation(summary = "Insert Credit transaction")
    public ResponseEntity<?> insertCreditTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new CREDIT transaction");
        transactionDTO.setIdTransactionType(Constants.CREDIT);
        return ResponseEntity.ok().body(transactionService.insertTransaction(transactionDTO));
    }

    @GetMapping
    @Operation(summary = "List all existing transactions")
    public ResponseEntity<?> listAllTransactions(){
        log.info("Listing all transactions");
        return ResponseEntity.ok().body(transactionService.listAllTransactions());
    }

    @GetMapping("/{date}")
    @Operation(summary = "List all existing transactions filtering by date")
    public ResponseEntity<?> listTransactionsByDate(@PathVariable("date") LocalDate date){
        log.info("Listing all transactions of day {}", date);
        return ResponseEntity.ok().body(transactionService.listTransactionsByDate(date));
    }

}
