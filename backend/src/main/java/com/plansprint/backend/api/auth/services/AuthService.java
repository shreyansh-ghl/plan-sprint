package com.plansprint.backend.api.auth.services;

import com.plansprint.backend.api.auth.dtos.LoginReqDto;
import com.plansprint.backend.api.auth.dtos.RegisterReqDto;
import com.plansprint.backend.api.auth.dtos.RegisterRespDto;
import com.plansprint.backend.api.users.entities.UserEntity;
import com.plansprint.backend.api.users.enums.Role;
import com.plansprint.backend.api.users.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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

    public UserEntity authenticate(LoginReqDto loginReqDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginReqDto.getEmail(),
                        loginReqDto.getPassword()
                )
        );

        return userRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Something went wrong "));
    }
}
