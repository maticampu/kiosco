package com.example.mym.service;

import com.example.mym.dto.SaleDto;
import com.example.mym.entity.Product;
import com.example.mym.entity.ProductSale;
import com.example.mym.entity.Sale;
import com.example.mym.exception.ProductException;
import com.example.mym.repository.HistoricalProductRepository;
import com.example.mym.repository.ProductRepository;
import com.example.mym.repository.ProductSaleRepository;
import com.example.mym.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    public final SaleRepository saleRepository;
    public final HistoricalProductRepository historicalProductRepository;
    public final ProductRepository productRepository;
    public final ProductSaleRepository productSaleRepository;

    SaleService(SaleRepository saleRepository, HistoricalProductRepository historicalProductRepository, ProductRepository productRepository, ProductSaleRepository productSaleRepository) {
        this.saleRepository = saleRepository;
        this.historicalProductRepository = historicalProductRepository;
        this.productRepository = productRepository;
        this.productSaleRepository = productSaleRepository;
    }


    public List<SaleDto> createSale(List<SaleDto> newSaleList) {
        BigDecimal totalAmount = newSaleList.stream().map(saleItem ->
            {
                Optional<Product> productFound = productRepository.findActiveById(saleItem.getProductId());
                if (productFound.isEmpty()){
                    throw new ProductException("Unable to find product with id: " + saleItem.getProductId());
                }
                BigDecimal parcialAmount = historicalProductRepository.findLastPriceByProduct(productFound.get()).multiply(BigDecimal.valueOf(saleItem.getQuantity()));
                return parcialAmount;
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
        Sale saleToSaved = Sale.builder()
                .date(LocalDateTime.now())
                .amount(totalAmount)
                .build();
        Sale saleSaved = saleRepository.save(saleToSaved);
        List<ProductSale> saleList = newSaleList.stream().map(saleItem ->
                {
                    Optional<Product> productFound = productRepository.findActiveById(saleItem.getProductId());
                    if (productFound.isEmpty()){
                        throw new ProductException("Unable to find product with id: " + saleItem.getProductId());
                    }
                    ProductSale productSaleToSave = ProductSale.builder()
                            .sale(saleSaved)
                            .historicalProduct(historicalProductRepository.findByLastProduct(productFound.get()))
                            .quantity(saleItem.getQuantity())
                            .build();
                    return productSaleRepository.save(productSaleToSave);
                }
                ).toList();

        return saleList.stream().map(saleItem ->
                        SaleDto.builder()
                                .productId(saleItem.getHistoricalProduct().getProduct().getProductId())
                                .quantity(saleItem.getQuantity())
                                .build()
                ).toList();
    }
}
