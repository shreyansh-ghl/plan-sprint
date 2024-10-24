package com.plansprint.backend.auth.dtos;

// TODO: apply validations
public class LoginUserDto {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
