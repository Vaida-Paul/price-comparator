package com.example.pricecomparatorapp.Dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateShoppingListRequest {
    private String name;
    private Long userId;
    private List<ShoppingListItemRequest> items;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<ShoppingListItemRequest> getItems() {
        return items;
    }
    public void setItems(List<ShoppingListItemRequest> items) {
        this.items = items;
    }
}
