package com.api.transactions.service;

import com.api.transactions.domain.model.TransactionType;
import com.api.transactions.exception.ItemNotFoundException;
import com.api.transactions.util.MockTransactionType;
import com.api.transactions.repository.TransactionTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TransactionTypeServiceTest {

    @Mock
    private TransactionTypeRepository transactionTypeRepository;

    @InjectMocks
    private TransactionTypeService transactionTypeService;

    @Test
    void when_listTransactionTypes_returnSuccess() {
        when(transactionTypeRepository.findAll()).thenReturn(List.of(MockTransactionType.mockDebitTransactionType()));

        List<TransactionType> transactionTypes = transactionTypeService.listTransactionTypes();

        Assertions.assertEquals(List.of(MockTransactionType.mockDebitTransactionType()), transactionTypes);
        verify(transactionTypeRepository, times(1)).findAll();
    }

    @Test
    void when_listTransactionTypes_throwsException() {
        when(transactionTypeRepository.findAll()).thenThrow(new ItemNotFoundException(""));

        assertThrows(ItemNotFoundException.class, () -> transactionTypeRepository.findAll());
        verify(transactionTypeRepository, times(1)).findAll();
    }
}