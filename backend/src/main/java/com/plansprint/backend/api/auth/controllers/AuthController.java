package com.plansprint.backend.api.auth.controllers;

import com.plansprint.backend.api.auth.dtos.*;
import com.plansprint.backend.api.auth.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterRespDto> register(@RequestBody RegisterReqDto registerReqDto) {
        RegisterRespDto registerRespDto = authService.signup(registerReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(registerRespDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRespDto> authenticate(@RequestBody LoginReqDto loginReqDto) {
        LoginRespDto loginRespDto = authService.login(loginReqDto);

        return ResponseEntity.ok(loginRespDto);
    }

    @GetMapping("/refresh")
    public ResponseEntity<RefreshTokenRespDto> refreshToken(@RequestParam String refreshToken) {
        RefreshTokenRespDto refreshTokenRespDto = authService.getRefreshedToken(refreshToken);

        return ResponseEntity.ok(refreshTokenRespDto);
    }
}
