package com.example.mym.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Product product;

    @NotNull
    private LocalDateTime updateDate;

    @NotNull
    @DecimalMin(value = "0", message = "el precio debe ser mayor que 0")
    private BigDecimal price;
}
