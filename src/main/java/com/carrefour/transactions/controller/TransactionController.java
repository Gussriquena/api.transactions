package com.carrefour.transactions.controller;

import com.carrefour.transactions.domain.dto.TransactionDTO;
import com.carrefour.transactions.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public ResponseEntity<?> insertTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new generic transaction with type ID: {}", transactionDTO.getIdTransactionType());
        return ResponseEntity.ok().body(transactionService.insertTransaction(transactionDTO));
    }

    @PostMapping("/debit")
    public ResponseEntity<?> insertDebitTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new DEBIT transaction");
        transactionDTO.setIdTransactionType(DEBIT);
        return ResponseEntity.ok().body(transactionService.insertTransaction(transactionDTO));
    }

    @PostMapping("/credit")
    public ResponseEntity<?> insertCreditTransaction(@RequestBody TransactionDTO transactionDTO){
        log.info("Inserting new CREDIT transaction");
        transactionDTO.setIdTransactionType(CREDIT);
        return ResponseEntity.ok().body(transactionService.insertTransaction(transactionDTO));
    }

    @GetMapping
    public ResponseEntity<?> listAllTransactions(){
        log.info("Listing all transactions");
        return ResponseEntity.ok().body(transactionService.listAllTransactions());
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> listTransactionsByDate(@PathVariable("date") LocalDate date){
        log.info("Listing all transactions of day {}", date);
        return ResponseEntity.ok().body(transactionService.listTransactionsByDate(date));
    }

}
