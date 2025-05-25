package com.example.pricecomparatorapp.Repository;

import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);


    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT d FROM Discount d WHERE d.product.id = :productId ORDER BY d.createdAt DESC")
    List<Discount> findPriceHistoryByProductId(@Param("productId") String productId);
}