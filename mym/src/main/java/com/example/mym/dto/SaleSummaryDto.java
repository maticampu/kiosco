package com.example.mym.dto;

import com.example.mym.supportClass.MeanOfPaymentAndAmount;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    private List<MeanOfPaymentAndAmount> meanOfPaymentAndAmount;

}
