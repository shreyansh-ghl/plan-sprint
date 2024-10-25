package com.plansprint.backend.api.users.dtos;

import com.plansprint.backend.api.users.enums.Role;

import java.time.LocalDateTime;

public class UserRespDto {
    private String id;

    private String email;

    private String name;

    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public String getId() {
        return id;
    }

    public UserRespDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRespDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRespDto setName(String name) {
        this.name = name;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserRespDto setRole(Role role) {
        this.role = role;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserRespDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserRespDto setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public UserRespDto setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
