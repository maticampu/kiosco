package com.example.mym.service;

import com.example.mym.dto.ProductDto;
import com.example.mym.entity.Product;
import com.example.mym.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getProductsByCode(String code) {
        List<Product> products = productRepository.findByCode(code);
        return products.stream().map((product) -> ProductDto.builder()
                .productId(product.getProductId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build())
        .toList() ;
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map((product) -> ProductDto.builder()
                .productId(product.getProductId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build()).toList();
        return productDtos;
    }

    public ProductDto saveProduct(ProductDto productDto) {
        Product product = Product.builder()
                .code(productDto.getCode())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
        Product productSaved= productRepository.save(product);
        ProductDto productDtoSaved = ProductDto.builder()
                .productId(productSaved.getProductId())
                .code(productSaved.getCode())
                .name(productSaved.getName())
                .price(productSaved.getPrice())
                .build();
        System.out.println(productDtoSaved);
        return productDtoSaved;
    }
}
