package com.api.transactions.controller;

import com.api.transactions.util.MockTransaction;
import com.api.transactions.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    private static final String URL_TRANSACTION = "/api/v1/transaction";

    @Test
    void when_insertTransaction_returnSuccess() throws Exception {
        when(transactionService.insertTransaction(any())).thenReturn(MockTransaction.mockDebitTransaction());

        mockMvc.perform(post(URL_TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MockTransaction.mockDebitTransactionDTO())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(MockTransaction.mockDebitTransaction())));

        verify(transactionService, times(1)).insertTransaction(any());
    }

    @Test
    void when_insertDebitTransaction_returnSuccess() throws Exception {
        when(transactionService.insertTransaction(any())).thenReturn(MockTransaction.mockDebitTransaction());

        mockMvc.perform(post(URL_TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MockTransaction.mockTransactionDTO())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(MockTransaction.mockDebitTransaction())));

        verify(transactionService, times(1)).insertTransaction(any());
    }

    @Test
    void when_insertCreditTransaction_returnSuccess() throws Exception {
        when(transactionService.insertTransaction(any())).thenReturn(MockTransaction.mockCreditTransaction());

        mockMvc.perform(post(URL_TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MockTransaction.mockTransactionDTO())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(MockTransaction.mockCreditTransaction())));

        verify(transactionService, times(1)).insertTransaction(any());
    }

    @Test
    void when_listAllTransactions_returnSuccess() throws Exception {
        when(transactionService.listAllTransactions()).thenReturn(List.of(MockTransaction.mockCreditTransaction()));

        mockMvc.perform(get(URL_TRANSACTION))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(MockTransaction.mockCreditTransaction()))));

        verify(transactionService, times(1)).listAllTransactions();
    }

    @Test
    void when_listTransactionsByDate_returnSuccess() throws Exception {
        when(transactionService.listTransactionsByDate(any())).thenReturn(List.of(MockTransaction.mockCreditTransaction()));

        mockMvc.perform(get(URL_TRANSACTION + "/2023-05-21"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(MockTransaction.mockCreditTransaction()))));

        verify(transactionService, times(1)).listTransactionsByDate(any());
    }
}