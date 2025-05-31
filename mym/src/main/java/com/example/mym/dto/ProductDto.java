package com.example.mym.dto;
import com.example.mym.entity.HistoricalProduct;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    Long productId;

    @NotBlank(message = "el nombre no puede ser vacio o en blanco")
    @Size(max = 150, message = "el tamaño maximo son 150 caracteres")
    String name;

    @NotBlank(message = "el codigo no puede ser vacio")
    @Size(max = 50, message = "el tamaño maximo son 150 caracteres")
    String code;

    @DecimalMin(value = "0", message = "el precio debe ser mayor que 0")
    @NotNull(message = "el precio no puede ser nulo")
    BigDecimal price;
}
