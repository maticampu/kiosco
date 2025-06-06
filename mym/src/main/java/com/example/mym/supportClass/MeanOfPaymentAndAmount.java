package com.example.mym.supportClass;

import com.example.mym.dto.MeanOfPayment;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class MeanOfPaymentAndAmount {

    MeanOfPayment meanOfPayment;

    int saleQuantity;

    BigDecimal amount;
}
