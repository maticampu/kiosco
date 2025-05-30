package com.example.mym.repository;

import com.example.mym.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product p WHERE p.code LIKE %:code% AND p.active = true order by p.productId")
    public List<Product> findProductsByCode(String code);

    @Query("SELECT p FROM Product p WHERE p.productId = :id AND p.active = true")
    Optional<Product> findActiveById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.active = false WHERE p.productId = :id")
    int logicalDeleteById(Long id);


    @Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.productId ASC")
    List<Product> findALlByActiveTrue();

    boolean existsByProductIdAndActiveTrue(Long productId);
}
