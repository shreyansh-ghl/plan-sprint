package com.plansprint.backend.api.auth.services;

import com.plansprint.backend.api.auth.dtos.LoginUserDto;
import com.plansprint.backend.api.auth.dtos.RegisterUserDto;
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

    public UserEntity signup(RegisterUserDto registerUserDto) {
        UserEntity userEntity = new UserEntity()
                .setEmail(registerUserDto.getEmail())
                .setPassword(passwordEncoder.encode(registerUserDto.getPassword()))
                .setRole(Role.USER); // set user as a normal user via signup flow

        return userRepository.save(userEntity);
    }

    public UserEntity authenticate(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );

        return userRepository.findByEmail(loginUserDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Something went wrong "));
    }
}
