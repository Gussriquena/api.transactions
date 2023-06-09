package com.api.transactions.service;

import com.api.transactions.domain.model.TransactionType;
import com.api.transactions.exception.ItemNotFoundException;
import com.api.transactions.repository.TransactionTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionTypeService {

    @Autowired
    private final TransactionTypeRepository transactionTypeRepository;

    public List<TransactionType> listTransactionTypes(){
        try {
            log.info("Listing Transaction Types");
            return transactionTypeRepository.findAll();
        } catch(Exception e){
            throw new ItemNotFoundException(e.getMessage());
        }
    }
}
