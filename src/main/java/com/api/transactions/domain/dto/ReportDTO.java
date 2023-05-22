package com.api.transactions.domain.dto;

import java.time.LocalDate;

public interface ReportDTO {
    LocalDate getDate();
    String getName();
    int getTransactionsAmount();
}
