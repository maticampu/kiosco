package com.example.mym.repository;

import com.example.mym.entity.ProductSale;
import com.example.mym.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {

    //filtrar por columna
    @Query("SELECT ps FROM ProductSale ps WHERE ps.sale.date BETWEEN :start AND :end ")
    List<ProductSale> getProductsSaleByDate (LocalDateTime start, LocalDateTime end);

    @Query ("SELECT ps FROM ProductSale ps WHERE ps.sale IN :salesWithCondition")
    List<ProductSale> findBySaleIn(List<Sale> salesWithCondition);
}
