package com.example.mym.repository;

import com.example.mym.dto.MeanOfPayment;
import com.example.mym.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN :start AND :end")
    List<Sale> getSalesByDate(LocalDateTime start, LocalDateTime end);

    @Query(
            "SELECT v " +
                    "FROM Sale v " +
                    "WHERE (v.date >= :startDate) " +
                    "AND (v.date <= :endDate) " +
                    "AND (v.amount >= :startAmount) " +
                    "AND (v.amount <= :endAmount)" +
                    "AND (v.meanOfPayment = :meanOfPayment)"
    )
    List<Sale> getSalesByConditions(LocalDateTime startDate, LocalDateTime endDate, BigDecimal startAmount, BigDecimal endAmount, MeanOfPayment meanOfPayment);

    @Query(
            "SELECT v " +
                    "FROM Sale v " +
                    "WHERE (v.date >= :startDate) " +
                    "AND (v.date <= :endDate) " +
                    "AND (v.amount >= :startAmount) " +
                    "AND (v.amount <= :endAmount)"
    )
    List<Sale> getSalesByConditionsWithoutPayment(LocalDateTime startDate, LocalDateTime endDate, BigDecimal startAmount, BigDecimal endAmount);
}
