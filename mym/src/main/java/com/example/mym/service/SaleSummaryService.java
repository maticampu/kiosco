package com.example.mym.service;


import com.example.mym.dto.MeanOfPayment;
import com.example.mym.dto.SaleSummaryDto;
import com.example.mym.entity.ProductSale;
import com.example.mym.entity.Sale;
import com.example.mym.exception.ProductException;
import com.example.mym.repository.ProductSaleRepository;
import com.example.mym.repository.SaleRepository;
import com.example.mym.supportClass.MeanOfPaymentAndAmount;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
        Map<MeanOfPayment, List<Sale>> groupedByMeanOfPayment = todaySales.stream()
                .collect(Collectors.groupingBy(Sale::getMeanOfPayment));
        List<MeanOfPaymentAndAmount> result = new ArrayList<>();
        for (MeanOfPayment mean : MeanOfPayment.values()) {
            List<Sale> sales = groupedByMeanOfPayment.getOrDefault(mean, Collections.emptyList());

            int count = sales.size();
            BigDecimal total = sales.stream()
                    .map(Sale::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            result.add(new MeanOfPaymentAndAmount(mean, count, total));
        }
        return SaleSummaryDto.builder()
                .saleQuantity(totalSales)
                .amount(totalAmount)
                .productQuantity(totalProductQuantity)
                .meanOfPaymentAndAmount(result)
                .build();
    }
}

