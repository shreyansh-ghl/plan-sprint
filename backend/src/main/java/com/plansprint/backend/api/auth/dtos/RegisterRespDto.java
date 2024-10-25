package com.plansprint.backend.api.auth.dtos;

import com.plansprint.backend.api.users.enums.Role;

import java.time.LocalDateTime;

public class RegisterRespDto {
    private String id;

    private String email;

    private Role role;

    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public RegisterRespDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRespDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public RegisterRespDto setRole(Role role) {
        this.role = role;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public RegisterRespDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
