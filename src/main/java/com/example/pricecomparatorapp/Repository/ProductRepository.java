package com.example.pricecomparatorapp.Repository;

import com.example.pricecomparatorapp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);
}
