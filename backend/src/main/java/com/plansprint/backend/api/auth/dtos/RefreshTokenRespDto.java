package com.plansprint.backend.api.auth.dtos;

public class RefreshTokenRespDto {
    private String token;

    public String getToken() {
        return token;
    }

    public RefreshTokenRespDto setToken(String token) {
        this.token = token;
        return this;
    }
}
