package com.example.pricecomparatorapp.Repository;

import com.example.pricecomparatorapp.Model.PriceAlert;
import com.example.pricecomparatorapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceAlertRepository extends JpaRepository<PriceAlert, Long> {
    List<PriceAlert> findByUserId(Long userId);
    List<PriceAlert> findByUserIdAndIsActiveTrue(Long userId);
    boolean existsByUserIdAndProductId(Long userId, String productId);
}