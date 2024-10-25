package com.plansprint.backend.api.users.controllers;

import com.plansprint.backend.api.users.dtos.UserRespDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plansprint.backend.api.users.services.UserService;

import com.plansprint.backend.api.users.entities.UserEntity;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserRespDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        UserRespDto userRespDto = new UserRespDto()
                .setId(currentUser.getId())
                .setEmail(currentUser.getEmail())
                .setRole(currentUser.getRole())
                .setName(currentUser.getName())
                .setCreatedAt(currentUser.getCreatedAt())
                .setUpdatedAt(currentUser.getUpdatedAt())
                .setDeletedAt(currentUser.getDeletedAt());

        return ResponseEntity.ok(userRespDto);
    }
}
