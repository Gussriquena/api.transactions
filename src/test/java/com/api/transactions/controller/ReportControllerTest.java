package com.api.transactions.controller;

import com.api.transactions.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.api.transactions.util.MockReport.mockFinalReport;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReportService reportService;

    private static final String URL_REPORT = "/api/v1/report/daily";


    @Test
    void when_getDailyReport_returnSuccess() throws Exception {
        when(reportService.getDailyReport()).thenReturn(mockFinalReport());

        mockMvc.perform(get(URL_REPORT))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockFinalReport())));

        verify(reportService, times(1)).getDailyReport();
    }
}