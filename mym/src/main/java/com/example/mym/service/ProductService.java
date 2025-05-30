package com.example.mym.service;

import com.example.mym.dto.ProductDto;
import com.example.mym.entity.Product;
import com.example.mym.exception.ProductException;
import com.example.mym.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getProductsByCode(String code) {
        List<Product> products = productRepository.findProductsByCode(code);
        return products.stream().map(this::toProductDto).toList() ;
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findALlByActiveTrue();
        List<ProductDto> productDtos = products.stream().map(this::toProductDto).toList();
        return productDtos;
    }

    public ProductDto saveProduct(@Valid ProductDto productDto) {
        Product product = toProduct(productDto);
        product.setActive(true);
        Product productSaved= productRepository.save(product);
        ProductDto productDtoSaved = toProductDto(productSaved);
        return productDtoSaved;
    }

    public ProductDto updateProduct(ProductDto productDto) {
    if (!productRepository.existsByProductIdAndActiveTrue(productDto.getProductId())) {
            throw new ProductException("Product not found");
        }
        System.out.println(productDto.getProductId());
        Product product = toProduct(productDto);
        product.setActive(true);
        Product productUpdated= productRepository.save(product);
        ProductDto productDtoSaved = toProductDto(productUpdated);
        return productDtoSaved;
    }

    public ProductDto deleteProduct(Long productId) {
        Optional<Product> product = productRepository.findActiveById(productId);
        if (product.isEmpty()){
            throw new ProductException("Product not found");
        }
        productRepository.deleteById(productId);
        ProductDto productDeleted = toProductDto(product.get());
        return productDeleted;
    }

    public ProductDto softDeleteProduct(Long productId) {
        Optional<Product> product = productRepository.findActiveById(productId);
        if (product.isEmpty()){
            throw new ProductException("Product not found");
        }
        productRepository.logicalDeleteById(productId);
        ProductDto productDeleted = toProductDto(product.get());
        return productDeleted;
    }

    private ProductDto toProductDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .productId(product.getProductId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        return productDto;
    }

    private Product toProduct(ProductDto productDto) {
        Product product = Product.builder()
                .productId(productDto.getProductId())
                .code(productDto.getCode())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
        return product;
    }
}
