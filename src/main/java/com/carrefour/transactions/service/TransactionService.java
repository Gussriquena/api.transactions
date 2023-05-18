package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.dto.TransactionDTO;
import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.domain.model.Transaction;
import com.carrefour.transactions.domain.model.TransactionType;
import com.carrefour.transactions.repository.ProductRepository;
import com.carrefour.transactions.repository.TransactionRepository;
import com.carrefour.transactions.repository.TransactionTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private final ProductRepository productRepository;

    public Object insertDebitTransaction(TransactionDTO transactionDTO){
        Transaction transaction = createTransaction(transactionDTO);
        updateProductAmount(transaction);
        return transactionRepository.save(transaction);
    }

    private void updateProductAmount(Transaction transaction){
        int productCurrentAmount = transaction.getProduct().getAmount();
        int amountToUpdate = transaction.getTransactionAmount();
        String type = transaction.getTransactionType().getName();

        switch (type){
            case "debit" -> transaction.getProduct().setAmount(productCurrentAmount - amountToUpdate);
            case "credit" -> transaction.getProduct().setAmount(productCurrentAmount + amountToUpdate);
        }

        productRepository.save(transaction.getProduct());
    }

    private Transaction createTransaction(TransactionDTO transactionDTO){
        TransactionType type = transactionTypeRepository.findById(transactionDTO.getIdTransactionType()).get();
        Product product = productRepository.findById(transactionDTO.getIdProduct()).get();

        return Transaction.builder()
                .price(transactionDTO.getPrice())
                .transactionAmount(transactionDTO.getTransactionAmount())
                .transactionType(type)
                .product(product)
                .build();
    }
}
