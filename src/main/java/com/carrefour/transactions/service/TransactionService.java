package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.dto.TransactionDTO;
import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.domain.model.Transaction;
import com.carrefour.transactions.domain.model.TransactionType;
import com.carrefour.transactions.repository.ProductRepository;
import com.carrefour.transactions.repository.TransactionRepository;
import com.carrefour.transactions.repository.TransactionTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private final ProductRepository productRepository;

    public Transaction insertTransaction(TransactionDTO transactionDTO){
        log.info("Inserting Transaction");
        Transaction transaction = createTransaction(transactionDTO);
        updateProductAmount(transaction);
        return transactionRepository.save(transaction);
    }

    private void updateProductAmount(Transaction transaction){
        log.info("Updating amount of product: {}", transaction.getProduct().getName());
        int productCurrentAmount = transaction.getProduct().getAmount();
        int amountToUpdate = transaction.getAmount();
        String type = transaction.getTransactionType().getName();

        switch (type){
            case "debit" -> transaction.getProduct().setAmount(productCurrentAmount - amountToUpdate);
            case "credit" -> transaction.getProduct().setAmount(productCurrentAmount + amountToUpdate);
        }

        productRepository.save(transaction.getProduct());
    }

    private Transaction createTransaction(TransactionDTO transactionDTO){
        log.info("Searching TransactionType by ID {}", transactionDTO.getIdTransactionType());
        TransactionType type = transactionTypeRepository.findById(transactionDTO.getIdTransactionType()).get();

        log.info("Searching Product by ID {}", transactionDTO.getIdProduct());
        Product product = productRepository.findById(transactionDTO.getIdProduct()).get();

        log.info("Creating final Transaction");
        return Transaction.builder()
                .price(transactionDTO.getPrice())
                .amount(transactionDTO.getTransactionAmount())
                .transactionType(type)
                .product(product)
                .date(LocalDate.now())
                .build();
    }
}
