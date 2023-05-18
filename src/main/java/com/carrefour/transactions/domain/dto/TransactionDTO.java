package com.carrefour.transactions.domain.dto;

import com.carrefour.transactions.domain.model.Product;
import com.carrefour.transactions.domain.model.TransactionType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
