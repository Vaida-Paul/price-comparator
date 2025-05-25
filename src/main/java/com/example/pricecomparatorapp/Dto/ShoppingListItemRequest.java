package com.example.pricecomparatorapp.Dto;

import lombok.Data;

@Data
public class ShoppingListItemRequest {
    private String productId;
    private Integer quantity = 1;

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
