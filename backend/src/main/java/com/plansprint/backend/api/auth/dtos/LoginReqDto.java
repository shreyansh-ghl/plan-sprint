package com.plansprint.backend.api.auth.dtos;

// TODO: apply validations
public class LoginReqDto {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public LoginReqDto setEmail() {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginReqDto setPassword() {
        this.password = password;
        return this;
    }
}
