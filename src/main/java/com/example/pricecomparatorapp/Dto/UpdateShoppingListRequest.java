package com.example.pricecomparatorapp.Dto;

import lombok.Data;
import java.util.List;

@Data
public class UpdateShoppingListRequest {
    private String name;
    private List<ShoppingListItemRequest> items;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ShoppingListItemRequest> getItems() {
        return items;
    }
    public void setItems(List<ShoppingListItemRequest> items) {
        this.items = items;
    }
}
