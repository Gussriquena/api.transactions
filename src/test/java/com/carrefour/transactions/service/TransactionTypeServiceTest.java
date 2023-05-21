package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.model.TransactionType;
import com.carrefour.transactions.exception.ItemNotFoundException;
import com.carrefour.transactions.repository.TransactionTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.carrefour.transactions.util.MockTransactionType.mockDebitTransactionType;
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
        when(transactionTypeRepository.findAll()).thenReturn(List.of(mockDebitTransactionType()));

        List<TransactionType> transactionTypes = transactionTypeService.listTransactionTypes();

        assertEquals(List.of(mockDebitTransactionType()), transactionTypes);
        verify(transactionTypeRepository, times(1)).findAll();
    }

    @Test
    void when_listTransactionTypes_throwsException() {
        when(transactionTypeRepository.findAll()).thenThrow(new ItemNotFoundException(""));

        assertThrows(ItemNotFoundException.class, () -> transactionTypeRepository.findAll());
        verify(transactionTypeRepository, times(1)).findAll();
    }
}