package com.example.InaiProject.services.impl;

import com.example.InaiProject.dto.auth.AuthLoginRequest;
import com.example.InaiProject.dto.auth.AuthRegisterRequest;
import com.example.InaiProject.dto.auth.AuthResponse;
import com.example.InaiProject.exception.CustomException;
import com.example.InaiProject.mapper.AuthMapper;
import com.example.InaiProject.model.User;
import com.example.InaiProject.repository.UserRepository;
import com.example.InaiProject.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(AuthRegisterRequest authRegisterRequest) {
        if(userRepository.findByEmail(authRegisterRequest.getEmail()).isPresent()) {
            throw new CustomException("User with this email is already exist", HttpStatus.ALREADY_REPORTED);
        }
        return authMapper.toDto(userRepository.save(authMapper.toDtoUser(authRegisterRequest)));
    }

    @Override
    public AuthResponse login(AuthLoginRequest authLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLoginRequest.getEmail(),
                        authLoginRequest.getPassword()
                )
        );
        User user = userRepository.findByEmail(authLoginRequest.getEmail()).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        return authMapper.toDto(user);
    }
}
