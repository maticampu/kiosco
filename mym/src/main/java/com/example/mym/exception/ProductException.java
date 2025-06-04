package com.example.mym.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter

public class ProductException extends RuntimeException {

    public ProductException(String message) {
        super(message);
    }
}
