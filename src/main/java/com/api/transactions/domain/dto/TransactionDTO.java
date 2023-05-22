package com.api.transactions.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private BigDecimal price;
    private int transactionAmount;
    private Long idProduct;
    private Long idTransactionType;

}
