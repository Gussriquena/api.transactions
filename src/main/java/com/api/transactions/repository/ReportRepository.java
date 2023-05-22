package com.api.transactions.repository;

import com.api.transactions.domain.dto.ReportDTO;
import com.api.transactions.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Transaction, Integer> {

    @Query(value =
            "SELECT t.date, tt.name, COUNT(t.date) AS transactionsAmount " +
            "FROM transaction t " +
            "JOIN transaction_type tt " +
            "ON t.id_transaction_type_fk = tt.id_transaction " +
            "WHERE t.date IS NOT NULL and tt.name IS NOT NULL " +
            "GROUP BY t.date, tt.name",
            nativeQuery = true)
    List<ReportDTO> getDailyReport();

}
