package com.plansprint.backend.api.auth.controllers;

import com.plansprint.backend.api.auth.dtos.LoginRespDto;
import com.plansprint.backend.api.auth.dtos.LoginReqDto;
import com.plansprint.backend.api.auth.dtos.RegisterReqDto;
import com.plansprint.backend.api.auth.dtos.RegisterRespDto;
import com.plansprint.backend.api.auth.services.AuthService;
import com.plansprint.backend.api.common.services.JwtService;
import com.plansprint.backend.api.users.entities.UserEntity;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<RegisterRespDto> register(@RequestBody RegisterReqDto registerReqDto) {
        RegisterRespDto registerRespDto = authService.signup(registerReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(registerRespDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRespDto> authenticate(@RequestBody LoginReqDto loginReqDto) {
        UserEntity authenticatedUser = authService.authenticate(loginReqDto);

        // TODO: improve data type casting
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", authenticatedUser.getRole());
        String jwtToken = jwtService.generateToken(claims, authenticatedUser.getEmail());

        LoginRespDto loginRespDto = new LoginRespDto()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationInMs());

        return ResponseEntity.ok(loginRespDto);
    }
}
