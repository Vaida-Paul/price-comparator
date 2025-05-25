package com.example.pricecomparatorapp.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShoppingListResponse {
    private Long id;
    private String name;
    private UserDto user;
    private List<ShoppingListItemDto> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
    public List<ShoppingListItemDto> getItems() {
        return items;
    }
    public void setItems(List<ShoppingListItemDto> items) {
        this.items = items;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}