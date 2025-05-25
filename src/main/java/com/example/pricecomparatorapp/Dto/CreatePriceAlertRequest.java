package com.example.pricecomparatorapp.Dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePriceAlertRequest {
    private Long userId;

    private String productId;

    @DecimalMin(value = "0.01", message = "Target price must be greater than 0")
    private BigDecimal targetPrice;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public BigDecimal getTargetPrice() {
        return targetPrice;
    }
    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

}
