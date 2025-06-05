package com.example.mym.repository;

import com.example.mym.entity.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    @Query("SELECT ps FROM ProductSale ps WHERE DATE(ps.sale.date) = :date")
    public List<ProductSale> getProductsSaleByDate (LocalDate date);
}
