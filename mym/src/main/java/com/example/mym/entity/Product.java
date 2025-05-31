package com.example.mym.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull(message = "el codigo no puede ser vacio")
    @Size(max = 50, message = "el tamaño maximo son 150 caracteres")
    private String code;

    @NotNull(message = "el nombre no puede ser vacio ")
    @Size(max = 150, message = "el tamaño maximo son 150 caracteres")
    private String name;

    @Builder.Default
    private boolean active = true;
}
