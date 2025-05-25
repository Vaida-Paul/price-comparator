package com.example.pricecomparatorapp.Dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private Long userId;
    private String username;

    public AuthResponse(String token, Long userId, String username) {
        this.token = token;
        this.userId = userId;
        this.username = username;
    }

    public String getToken() {
        return token;
    }
    private String getUsername() {
        return username;
    }
    public Long getUserId() {
        return userId;
    }


}