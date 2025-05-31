package com.example.mym.repository;


import com.example.mym.entity.HistoricalProduct;
import com.example.mym.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoricalProductRepository extends JpaRepository<HistoricalProduct, Long> {
    @Query("SELECT hp FROM HistoricalProduct hp WHERE hp.product = :product AND hp.updateDate = (SELECT MAX(hp2.updateDate) FROM HistoricalProduct hp2 WHERE hp2.product = :product)")

    HistoricalProduct findByLastProduct(Product product);

}
