package com.example.mym.service;

import com.example.mym.dto.ProductDto;
import com.example.mym.entity.HistoricalProduct;
import com.example.mym.entity.Product;
import com.example.mym.exception.ProductException;
import com.example.mym.repository.HistoricalProductRepository;
import com.example.mym.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    HistoricalProductRepository historicalProductRepository;

    public ProductService(ProductRepository productRepository, HistoricalProductRepository historicalProductRepository)
    {
        this.productRepository = productRepository;
        this.historicalProductRepository = historicalProductRepository;
    }

    public List<ProductDto> getProductsByCode(String code) {
        List<Product> products = productRepository.findProductsByCode(code);
        return products.stream().map(a -> {
                ProductDto productToShow = toProductDto(a);
                productToShow.setPrice(historicalProductRepository.findByLastProduct(a).getPrice());
                return productToShow;})
                    .toList();
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findALlByActiveTrue();
        List<ProductDto> productDtos = products.stream().map(a -> {
                    ProductDto productToShow = toProductDto(a);
                    productToShow.setPrice(historicalProductRepository.findByLastProduct(a).getPrice());
                    return productToShow;
                })
                .toList();
        return productDtos;
    }

    @Transactional
    public ProductDto saveProduct(ProductDto productToSave){
        Product productToSaved = toProduct(productToSave);
        Product productSaved = productRepository.save(productToSaved);
        HistoricalProduct historicalProductToSave = HistoricalProduct.builder()
                .price(productToSave.getPrice())
                .product(productSaved)
                .updateDate(LocalDateTime.now())
                .build();
        HistoricalProduct historicalProductSaved = historicalProductRepository.save(historicalProductToSave);
        ProductDto productDtoSaved = toProductDto(productSaved);
        productDtoSaved.setPrice(historicalProductSaved.getPrice());
        productSaved.setHistoricalProduct(historicalProductSaved);
        productRepository.save(productSaved);
        return productDtoSaved;
    }

    public ProductDto updateProduct(ProductDto productToSave) {
        Product productFound = productRepository.findById(productToSave.getProductId())
                                                .orElseThrow(() -> new ProductException("Product not found"));
        HistoricalProduct historicalProductToSave = historicalProductRepository.findByLastProduct(productFound);
        BigDecimal currentPrice = historicalProductToSave.getPrice();
        if (productToSave.getPrice().compareTo(currentPrice) != 0){
            historicalProductToSave = HistoricalProduct.builder()
                    .product(productFound)
                    .price(productToSave.getPrice())
                    .updateDate(LocalDateTime.now())
                    .build();
            historicalProductRepository.save(historicalProductToSave);
        }
            Product product = toProduct(productToSave);
            product.setHistoricalProduct(historicalProductToSave);
            Product productUpdated = productRepository.save(product);
            ProductDto productDtoUpdated = toProductDto(productUpdated);
            productDtoUpdated.setPrice(historicalProductRepository.findByLastProduct(productUpdated).getPrice());
            return productDtoUpdated;
    }

    @Transactional
    public ProductDto deleteProduct(Long productId) {
        Optional<Product> product = productRepository.findActiveById(productId);
        if (product.isEmpty()){
            throw new ProductException("Product not found");
        }
        productRepository.deleteById(productId);
        ProductDto productDeleted = toProductDto(product.get());
        return productDeleted;
    }

    @Transactional
    public ProductDto softDeleteProduct(Long productId) {
        Optional<Product> product = productRepository.findActiveById(productId);
        if (product.isEmpty()){
            throw new ProductException("Product not found");
        }
        productRepository.softDeleteById(productId);
        ProductDto productDeleted = toProductDto(product.get());
        productDeleted.setPrice(historicalProductRepository.findByLastProduct(product.get()).getPrice());
        return productDeleted;
    }

    private ProductDto toProductDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .productId(product.getProductId())
                .code(product.getCode())
                .name(product.getName())
                .build();
        return productDto;
    }

    private Product toProduct(ProductDto productDto) {
        Product product = Product.builder()
                .productId(productDto.getProductId())
                .code(productDto.getCode())
                .name(productDto.getName())
                .build();
        return product;
    }

}
