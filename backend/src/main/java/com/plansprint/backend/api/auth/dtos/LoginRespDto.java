package com.plansprint.backend.api.auth.dtos;

public class LoginRespDto {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public LoginRespDto setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginRespDto setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }
}
