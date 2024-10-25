package com.plansprint.backend.api.auth.dtos;

// TODO: apply validations
public class RegisterReqDto {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public RegisterReqDto setEmail() {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterReqDto setPassword() {
        this.password = password;
        return this;
    }
}
