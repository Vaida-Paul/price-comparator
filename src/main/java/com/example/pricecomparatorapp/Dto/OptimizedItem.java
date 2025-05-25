package com.example.pricecomparatorapp.Dto;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class OptimizedItem {
    private String productId;
    private String productName;
    private Integer quantity;
    private String storeName;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private BigDecimal discountPercentage;

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }
    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
