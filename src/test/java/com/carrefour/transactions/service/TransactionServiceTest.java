package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.model.Transaction;
import com.carrefour.transactions.exception.FailedInsertingItem;
import com.carrefour.transactions.exception.ItemNotFoundException;
import com.carrefour.transactions.repository.ProductRepository;
import com.carrefour.transactions.repository.TransactionRepository;
import com.carrefour.transactions.repository.TransactionTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.carrefour.transactions.util.MockProduct.mockProduct;
import static com.carrefour.transactions.util.MockTransaction.mockDebitTransaction;
import static com.carrefour.transactions.util.MockTransaction.mockDebitTransactionDTO;
import static com.carrefour.transactions.util.MockTransactionType.mockDebitTransactionType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TransactionServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private TransactionTypeRepository transactionTypeRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private static final LocalDate MOCK_DATE = LocalDate.of(2023, 5, 21);

    @Test
    void when_insertTransaction_returnSuccess() {
        when(transactionRepository.save(any())).thenReturn(mockDebitTransaction());
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(mockProduct()));
        when(transactionTypeRepository.findById(any())).thenReturn(Optional.ofNullable(mockDebitTransactionType()));

        Transaction transaction = transactionService.insertTransaction(mockDebitTransactionDTO());

        assertEquals(mockDebitTransaction(), transaction);
        verify(transactionRepository, times(1)).save(any());
        verify(productRepository, times(1)).findById(any());
        verify(transactionTypeRepository, times(1)).findById(any());
    }

    @Test
    void when_insertTransaction_throwsException() {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(mockProduct()));
        when(transactionTypeRepository.findById(any())).thenReturn(Optional.ofNullable(mockDebitTransactionType()));
        when(transactionRepository.save(any())).thenThrow(new FailedInsertingItem(""));

        assertThrows(FailedInsertingItem.class, () -> transactionService.insertTransaction(mockDebitTransactionDTO()));
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void when_listAllTransactions_returnSuccess() {
        when(transactionRepository.findAll()).thenReturn(List.of(mockDebitTransaction()));

        List<Transaction> transactions = transactionService.listAllTransactions();

        assertEquals(List.of(mockDebitTransaction()), transactions);
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void when_listAllTransactions_throwsException() {
        when(transactionRepository.findAll()).thenThrow(new ItemNotFoundException(""));

        assertThrows(ItemNotFoundException.class, () -> transactionService.listAllTransactions());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void when_listTransactionsByDate_returnSuccess() {
        when(transactionRepository.findByDate(any())).thenReturn(List.of(mockDebitTransaction()));

        List<Transaction> transactions = transactionService.listTransactionsByDate(MOCK_DATE);

        assertEquals(List.of(mockDebitTransaction()), transactions);
        verify(transactionRepository, times(1)).findByDate(any());
    }

    @Test
    void when_listTransactionsByDate_throwsException() {
        when(transactionRepository.findByDate(any())).thenThrow(new ItemNotFoundException(""));

        assertThrows(ItemNotFoundException.class, () -> transactionService.listTransactionsByDate(MOCK_DATE));
        verify(transactionRepository, times(1)).findByDate(any());
    }
}