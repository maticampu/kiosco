package com.example.mym.repository;

import com.example.mym.dto.SaleSummaryDto;
import com.example.mym.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN :start AND :end")
    List<Sale> getSalesByDate(LocalDateTime start, LocalDateTime end);
}
