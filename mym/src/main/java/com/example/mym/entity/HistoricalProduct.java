package com.example.mym.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historicalProductId;

    @ManyToOne
    private Product product;

    private LocalDateTime updateDate;

    private BigDecimal price;
}
