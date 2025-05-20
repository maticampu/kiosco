package com.example.mym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Builder

public class SaleDto {
    ArrayList<SaleDto> saleDtos;
    LocalDateTime saleDate;
}
