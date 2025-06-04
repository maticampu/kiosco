package com.example.mym.controller;

import com.example.mym.dto.ProductDto;
import com.example.mym.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    public ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{code}")
    @ResponseBody
    public List<ProductDto> getProductsByCode(@PathVariable String code) {
        return productService.getProductsByCode(code);
    }

    @PostMapping
    @ResponseBody
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping
    @ResponseBody
    public ProductDto updateProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/hardDelete/{productId}")
    @ResponseBody
    public ProductDto deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseBody
    public ProductDto softDeleteProduct(@PathVariable Long productId) {
        return productService.softDeleteProduct(productId);
    }
}
