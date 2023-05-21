package com.carrefour.transactions.util;

import com.carrefour.transactions.domain.dto.ReportDTO;

import java.time.LocalDate;
import java.util.Map;

public class MockReport {

    public static Map<LocalDate, Map<String, Integer>> mockFinalReport(){
        Map<String, Integer> firstDayAmount = Map.of(
                "debit", 5,
                "credit", 5
        );

        return Map.of(
                LocalDate.of(2023, 5, 21), firstDayAmount
        );
    }

    public static ReportDTO mockDebitReportDTO(){
        return new ReportDTO() {
            @Override
            public LocalDate getDate() {
                return LocalDate.of(2023, 5, 21);
            }

            @Override
            public String getName() {
                return "debit";
            }

            @Override
            public int getTransactionsAmount() {
                return 5;
            }
        };
    }

    public static ReportDTO mockCreditReportDTO(){
        return new ReportDTO() {
            @Override
            public LocalDate getDate() {
                return LocalDate.of(2023, 5, 21);
            }

            @Override
            public String getName() {
                return "credit";
            }

            @Override
            public int getTransactionsAmount() {
                return 5;
            }
        };
    }


}
