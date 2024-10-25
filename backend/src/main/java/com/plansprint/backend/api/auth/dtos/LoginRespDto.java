package com.plansprint.backend.api.auth.dtos;

public class LoginRespDto {
    private String accessToken;

    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public LoginRespDto setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public LoginRespDto setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
