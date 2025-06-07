package com.example.mym.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleSummaryWithConditionsDto{

    LocalDate startDate;

    LocalDate endDate;

    int minimumProductQuantity;

    BigDecimal minimumAmount;

    BigDecimal maximumAmount;

    MeanOfPayment meanOfPayment;

}
