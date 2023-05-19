package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.model.TransactionType;
import com.carrefour.transactions.repository.TransactionTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionTypeService {

    @Autowired
    private final TransactionTypeRepository transactionTypeRepository;

    public List<TransactionType> listTrasactionTypes(){
        return transactionTypeRepository.findAll();
    }
}
