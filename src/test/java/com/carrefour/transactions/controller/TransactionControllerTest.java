package com.carrefour.transactions.controller;

import com.carrefour.transactions.service.TransactionService;
import com.carrefour.transactions.service.TransactionTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.carrefour.transactions.util.MockProduct.mockProduct;
import static com.carrefour.transactions.util.MockTransaction.*;
import static org.junit.jupiter.api.Assertions.*;
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
        when(transactionService.insertTransaction(any())).thenReturn(mockDebitTransaction());

        mockMvc.perform(post(URL_TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockDebitTransactionDTO())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockDebitTransaction())));

        verify(transactionService, times(1)).insertTransaction(any());
    }

    @Test
    void when_insertDebitTransaction_returnSuccess() throws Exception {
        when(transactionService.insertTransaction(any())).thenReturn(mockDebitTransaction());

        mockMvc.perform(post(URL_TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockTransactionDTO())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockDebitTransaction())));

        verify(transactionService, times(1)).insertTransaction(any());
    }

    @Test
    void when_insertCreditTransaction_returnSuccess() throws Exception {
        when(transactionService.insertTransaction(any())).thenReturn(mockCreditTransaction());

        mockMvc.perform(post(URL_TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockTransactionDTO())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockCreditTransaction())));

        verify(transactionService, times(1)).insertTransaction(any());
    }

    @Test
    void when_listAllTransactions_returnSuccess() throws Exception {
        when(transactionService.listAllTransactions()).thenReturn(List.of(mockCreditTransaction()));

        mockMvc.perform(get(URL_TRANSACTION))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(mockCreditTransaction()))));

        verify(transactionService, times(1)).listAllTransactions();
    }

    @Test
    void listTransactionsByDate() throws Exception {
        when(transactionService.listTransactionsByDate(any())).thenReturn(List.of(mockCreditTransaction()));

        mockMvc.perform(get(URL_TRANSACTION + "/2023-05-21"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(mockCreditTransaction()))));

        verify(transactionService, times(1)).listTransactionsByDate(any());
    }
}