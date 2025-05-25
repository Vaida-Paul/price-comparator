package com.example.pricecomparatorapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PriceHistoryDto {
    private LocalDate date;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private BigDecimal percentage;
    private String storeName;


    public PriceHistoryDto(LocalDate fromDate, BigDecimal originalPrice,
                           BigDecimal percentage, String storeName) {
        this.date = fromDate;
        this.originalPrice = originalPrice;
        this.discountedPrice = originalPrice.multiply(
                BigDecimal.ONE.subtract(percentage.divide(BigDecimal.valueOf(100))));
        this.percentage = percentage;
        this.storeName = storeName;
    }
    public LocalDate getDate() {
        return date;
    }
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }
    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }
    public BigDecimal getPercentage() {
        return percentage;
    }
    public String getStoreName() {
        return storeName;
    }

}