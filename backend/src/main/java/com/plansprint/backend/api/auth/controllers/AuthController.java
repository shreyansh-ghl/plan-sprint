package com.plansprint.backend.api.auth.controllers;

import com.plansprint.backend.api.auth.dtos.LoginResponseDto;
import com.plansprint.backend.api.auth.dtos.LoginUserDto;
import com.plansprint.backend.api.auth.dtos.RegisterUserDto;
import com.plansprint.backend.api.auth.services.AuthService;
import com.plansprint.backend.api.common.services.JwtService;
import com.plansprint.backend.api.users.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

        // TODO: improve data type casting
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", authenticatedUser.getRole());
        String jwtToken = jwtService.generateToken(claims, authenticatedUser.id);

        LoginResponseDto loginResponseDto = new LoginResponseDto().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationInMs());

        return ResponseEntity.ok(loginResponseDto);
    }
}
