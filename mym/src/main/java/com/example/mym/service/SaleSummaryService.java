package com.example.mym.service;


import com.example.mym.dto.SaleSummaryDto;
import com.example.mym.entity.ProductSale;
import com.example.mym.entity.Sale;
import com.example.mym.exception.ProductException;
import com.example.mym.repository.ProductSaleRepository;
import com.example.mym.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleSummaryService {

    SaleRepository saleRepository;
    ProductSaleRepository productSaleRepository;

    public SaleSummaryService(SaleRepository saleRepository,  ProductSaleRepository productSaleRepository) {
        this.saleRepository = saleRepository;
        this.productSaleRepository = productSaleRepository;
    }

    public SaleSummaryDto getDailySaleSummary(){
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        List<Sale> todaySales = saleRepository.getSalesByDate(start, end);
        List<ProductSale> todayProductSales = productSaleRepository.getProductsSaleByDate(start, end);
        int totalSales = todaySales.size();
        BigDecimal totalAmount = todaySales.stream().map(Sale::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        int totalProductQuantity = todayProductSales.stream().mapToInt(ProductSale::getQuantity).sum();
        return SaleSummaryDto.builder()
                .saleQuantity(totalSales)
                .amount(totalAmount)
                .productQuantity(totalProductQuantity)
                .build();
    }
}

