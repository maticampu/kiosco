package com.example.mym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@Builder

public class ProductDto {
    String name;
    Long code;
    BigDecimal price;
    Long quantity;

}
