package com.example.mym.exception;

import com.example.mym.dto.ProductErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ProductErrorMessage> handleProductException(ProductException e) {
        ProductErrorMessage response = new ProductErrorMessage();
        response.setError(e.getMessage());

        if (e.getCode() == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (e.getCode() == 2)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ProductErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ProductErrorMessage response = new ProductErrorMessage();
        response.setError("El producto ingresado ya existe");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
