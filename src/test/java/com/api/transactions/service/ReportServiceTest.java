package com.api.transactions.service;

import com.api.transactions.exception.ItemNotFoundException;
import com.api.transactions.repository.ReportRepository;
import com.api.transactions.util.MockReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        when(reportRepository.getDailyReport()).thenReturn(List.of(MockReport.mockDebitReportDTO(), MockReport.mockCreditReportDTO()));

        Map<LocalDate, Map<String, Integer>> report = reportService.getDailyReport();

        Assertions.assertEquals(MockReport.mockFinalReport(), report);
        verify(reportRepository, times(1)).getDailyReport();
    }

    @Test
    void when_getDailyReport_throwsException() {
        when(reportRepository.getDailyReport()).thenThrow(new ItemNotFoundException(""));
        assertThrows(ItemNotFoundException.class, () -> reportService.getDailyReport());
        verify(reportRepository, times(1)).getDailyReport();
    }
}