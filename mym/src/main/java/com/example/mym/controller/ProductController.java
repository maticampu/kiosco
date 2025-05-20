package com.example.mym.controller;

import com.example.mym.dto.ProductDto;
import com.example.mym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/products")
public class ProductController {

    public ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ArrayList<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id]")
    public ArrayList<ProductDto> getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct();
    }

}
