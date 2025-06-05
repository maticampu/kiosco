package com.example.mym.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SaleSummaryDto {

    private int saleQuantity;

    private int productQuantity;

    private BigDecimal amount;

}
