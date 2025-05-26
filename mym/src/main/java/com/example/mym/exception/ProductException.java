package com.example.mym.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter

public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
