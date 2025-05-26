package com.example.mym.repository;

import com.example.mym.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product p where p.code LIKE %:code%")
    public List<Product> findProductsByCode(String code);
}
