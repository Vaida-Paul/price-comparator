package com.example.pricecomparatorapp.Dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PriceAlertDto {
    private Long id;
    private String productId;
    private String productName;
    private BigDecimal currentPrice;
    private BigDecimal targetPrice;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime triggeredAt;

    public PriceAlertDto(Long id, String productId, String productName, BigDecimal currentPrice, BigDecimal targetPrice, Boolean isActive, LocalDateTime createdAt, LocalDateTime triggeredAt) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.currentPrice = currentPrice;
        this.targetPrice = targetPrice;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.triggeredAt = triggeredAt;
    }
    public PriceAlertDto() {
        // Default constructor
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
    public BigDecimal getTargetPrice() {
        return targetPrice;
    }
    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }
    public void setTriggeredAt(LocalDateTime triggeredAt) {
        this.triggeredAt = triggeredAt;
    }
}
