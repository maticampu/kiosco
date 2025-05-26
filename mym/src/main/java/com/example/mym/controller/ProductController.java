package com.example.mym.controller;

import com.example.mym.dto.ProductDto;
import com.example.mym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    public ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{code}")
    public List<ProductDto> getProductsByCode(@PathVariable String code) {
        return productService.getProductsByCode(code);
    }

    @PostMapping
    @ResponseBody
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

}
