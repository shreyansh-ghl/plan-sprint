package com.plansprint.backend.api.auth.services;

import com.plansprint.backend.api.auth.dtos.*;
import com.plansprint.backend.api.common.TokenType;
import com.plansprint.backend.api.common.services.JwtService;
import com.plansprint.backend.api.users.entities.UserEntity;
import com.plansprint.backend.api.users.enums.Role;
import com.plansprint.backend.api.users.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public RegisterRespDto signup(RegisterReqDto registerReqDto) {
        UserEntity userEntity = new UserEntity()
                .setEmail(registerReqDto.getEmail())
                .setPassword(passwordEncoder.encode(registerReqDto.getPassword()))
                .setRole(Role.USER); // set user as a normal user via signup flow

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return new RegisterRespDto()
                .setId(savedUserEntity.getId())
                .setEmail(savedUserEntity.getEmail())
                .setRole(savedUserEntity.getRole())
                .setCreatedAt(savedUserEntity.getCreatedAt());
    }

    private UserEntity authenticate(LoginReqDto loginReqDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginReqDto.getEmail(),
                        loginReqDto.getPassword()
                )
        );

        return userRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Unable to authenticate user."));
    }

    public LoginRespDto login(LoginReqDto loginReqDto) {
        UserEntity authenticatedUser = this.authenticate(loginReqDto);
        String accessToken = this.generateJwtToken(authenticatedUser.getEmail(), TokenType.ACCESS);
        String refreshToken = this.generateJwtToken(authenticatedUser.getEmail(), TokenType.REFRESH);

        return new LoginRespDto()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken);
    }

    public RefreshTokenRespDto getRefreshedToken(String refreshToken) {
        String email = jwtService.getSubjectFromToken(refreshToken);
        String token = this.generateJwtToken(email, TokenType.REFRESH);

        return new RefreshTokenRespDto().setToken(token);
    }

    public String generateJwtToken(String email, TokenType tokenType) {
        UserEntity uerEntity = userRepository.findByEmail(email).orElseThrow();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", uerEntity.getRole());

        return jwtService.buildToken(uerEntity.getEmail(), claims, tokenType);
    }
}
