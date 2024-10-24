package com.plansprint.backend.api.auth.dtos;

// TODO: apply validations
public class RegisterUserDto {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
