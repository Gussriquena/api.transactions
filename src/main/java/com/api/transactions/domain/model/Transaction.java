package com.api.transactions.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;
    private BigDecimal price;
    private int amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_product_FK")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_transaction_type_FK")
    private TransactionType transactionType;
}
