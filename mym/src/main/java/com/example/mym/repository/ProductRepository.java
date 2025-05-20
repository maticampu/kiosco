package com.example.mym.repository;

import com.example.mym.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends JpaRepository<ProductDto, Long>{
}
