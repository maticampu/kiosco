package com.example.mym.service;

import com.example.mym.controller.ProductController;
import com.example.mym.dto.ProductDto;
import com.example.mym.entity.Product;
import com.example.mym.exception.ProductException;
import com.example.mym.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(this::toProductDto).toList();
        return productDtos;
    }

    public ProductDto saveProduct(ProductDto productDto) {
        if (productDto.getCode() == null) {
            throw new ProductException("Product code is required", 1);
        }
        if (productDto.getName() == null) {
            throw new ProductException("Product name is required", 1);
        }
        if (productDto.getPrice() == null) {
            throw new ProductException("Product price is required", 1);
        }
        Product product = toProduct(productDto);
        Product productSaved= productRepository.save(product);
        ProductDto productDtoSaved = toProductDto(productSaved);
        return productDtoSaved;
    }

    public ProductDto updateProduct(ProductDto productDto) {

        //Como se pasa el id del producto, cuando el front mande el updated, va a tener el id
        if (productDto.getProductId() == null) {
            throw new ProductException("Product id is required", 1);
        }
        if (productDto.getCode() == null) {
            throw new ProductException("Product code is required", 1);
        }
        if (productDto.getName() == null) {
            throw new ProductException("Product name is required", 1);
        }
        if (productDto.getPrice() == null) {
            throw new ProductException("Product price is required", 1);
        }
        if (!productRepository.existsById(productDto.getProductId())) {
            throw new ProductException("Product not found", 1);
        }
        Product product = toProduct(productDto);
        Product productUpdated= productRepository.save(product);
        ProductDto productDtoSaved = toProductDto(productUpdated);
        return productDtoSaved;
    }

    public ProductDto deleteProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()){
            throw new ProductException("Product not found", 2);
        }

        productRepository.deleteById(productId);
        return toProductDto(product.get());
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
