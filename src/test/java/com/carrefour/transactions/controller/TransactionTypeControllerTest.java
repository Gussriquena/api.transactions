package com.carrefour.transactions.controller;

import com.carrefour.transactions.service.TransactionTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.carrefour.transactions.util.MockTransactionType.mockDebitTransactionType;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionTypeController.class)
class TransactionTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionTypeService transactionTypeService;

    private static final String URL_TRANSACTION_TYPE = "/api/v1/transaction-type";


    @Test
    void when_listTransactionTypes_returnSuccess() throws Exception {
        when(transactionTypeService.listTransactionTypes()).thenReturn(List.of(mockDebitTransactionType()));

        mockMvc.perform(get(URL_TRANSACTION_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(mockDebitTransactionType()))));

        verify(transactionTypeService, times(1)).listTransactionTypes();
    }
}