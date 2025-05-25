package com.example.pricecomparatorapp.Repository;

import com.example.pricecomparatorapp.Dto.PriceHistoryDto;
import com.example.pricecomparatorapp.Model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.fromDate <= CURRENT_DATE AND d.toDate >= CURRENT_DATE")
    List<Discount> findCurrentDiscounts();

    @Query("SELECT d FROM Discount d WHERE d.fromDate <= CURRENT_DATE AND d.toDate >= CURRENT_DATE ORDER BY d.percentage DESC")
    List<Discount> findBestDiscounts();

    @Query("SELECT d FROM Discount d WHERE d.createdAt >= :cutoffTime")
    List<Discount> findNewDiscounts(@Param("cutoffTime") LocalDateTime cutoffTime);

    @Query("SELECT d FROM Discount d WHERE d.store.name = :storeName AND d.fromDate <= CURRENT_DATE AND d.toDate >= CURRENT_DATE")
    List<Discount> findByStore(@Param("storeName") String storeName);

    @Query("SELECT d FROM Discount d WHERE d.product.category = :category AND d.fromDate <= CURRENT_DATE AND d.toDate >= CURRENT_DATE")
    List<Discount> findByCategory(@Param("category") String category);

    @Query("SELECT NEW com.example.pricecomparatorapp.Dto.PriceHistoryDto(" +
            "d.fromDate, d.originalPrice, d.percentage, d.store.name) " +
            "FROM Discount d WHERE d.product.id = :productId " +
            "ORDER BY d.fromDate DESC")
    List<PriceHistoryDto> findPriceHistoryByProductId(@Param("productId") String productId);


    @Query("SELECT d FROM Discount d WHERE d.product.id = :productId")
    List<Discount> findByProductId(@Param("productId") String productId);

    @Query("SELECT d FROM Discount d WHERE d.product.id = :productId AND d.fromDate <= CURRENT_DATE AND d.toDate >= CURRENT_DATE")
    List<Discount> findCurrentDiscountsByProductId(@Param("productId") String productId);
}