package com.example.mym.supportClass;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductAndQuantity {

    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private int quantity;
}
