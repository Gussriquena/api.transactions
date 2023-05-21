package com.carrefour.transactions.service;

import com.carrefour.transactions.exception.ItemNotFoundException;
import com.carrefour.transactions.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.carrefour.transactions.util.MockReport.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    void when_getDailyReport_returnSuccess() {
        when(reportRepository.getDailyReport()).thenReturn(List.of(mockDebitReportDTO(), mockCreditReportDTO()));

        Map<LocalDate, Map<String, Integer>> report = reportService.getDailyReport();

        assertEquals(mockFinalReport(), report);
        verify(reportRepository, times(1)).getDailyReport();
    }

    @Test
    void when_getDailyReport_throwsException() {
        when(reportRepository.getDailyReport()).thenThrow(new ItemNotFoundException(""));
        assertThrows(ItemNotFoundException.class, () -> reportService.getDailyReport());
        verify(reportRepository, times(1)).getDailyReport();
    }
}