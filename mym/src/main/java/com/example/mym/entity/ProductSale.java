package com.example.mym.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSaleId;

    @ManyToOne
    private Sale sale;

    @ManyToOne
    private HistoricalProduct historicalProduct;

    private int quantity;


}
