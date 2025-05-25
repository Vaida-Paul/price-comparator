package com.example.pricecomparatorapp.Dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
@Data
public class OptimizedShoppingListResponse {
    private Long shoppingListId;
    private String name;
    private BigDecimal totalOriginalPrice;
    private BigDecimal totalDiscountedPrice;
    private BigDecimal totalSavings;
    private List<OptimizedItem> optimizedItems;

    public Long getShoppingListId() {
        return shoppingListId;
    }
    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getTotalOriginalPrice() {
        return totalOriginalPrice;
    }
    public void setTotalOriginalPrice(BigDecimal totalOriginalPrice) {
        this.totalOriginalPrice = totalOriginalPrice;
    }
    public BigDecimal getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }
    public void setTotalDiscountedPrice(BigDecimal totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }
    public BigDecimal getTotalSavings() {
        return totalSavings;
    }
    public void setTotalSavings(BigDecimal totalSavings) {
        this.totalSavings = totalSavings;
    }
    public List<OptimizedItem> getOptimizedItems() {
        return optimizedItems;
    }
    public void setOptimizedItems(List<OptimizedItem> optimizedItems) {
        this.optimizedItems = optimizedItems;
    }

}