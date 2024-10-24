package com.plansprint.backend.auth.controllers;

import com.plansprint.backend.auth.dtos.LoginResponseDto;
import com.plansprint.backend.auth.dtos.LoginUserDto;
import com.plansprint.backend.auth.dtos.RegisterUserDto;
import com.plansprint.backend.auth.services.AuthService;
import com.plansprint.backend.common.services.JwtService;
import com.plansprint.backend.users.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken((Map<String, Object>) authenticatedUser, authenticatedUser.id); // TODO: improve data type

        LoginResponseDto loginResponseDto = new LoginResponseDto().setToken(jwtToken).setExpiresIn(jwtService.getExpirationInMs());

        return ResponseEntity.ok(loginResponseDto);
    }
}
