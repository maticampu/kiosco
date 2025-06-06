package com.example.mym.dto;

import com.example.mym.supportClass.ProductAndQuantity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SaleDto {

    @NotNull
    List<ProductAndQuantity> productAndQuantity;

    @NotNull
    private MeanOfPayment meanOfPayment;
}
