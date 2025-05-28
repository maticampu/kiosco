package com.example.mym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class SaleItemDto {

    ProductDto product;

    Long amount;

}
