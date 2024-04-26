package com.example.InaiProject.services;


import com.example.InaiProject.dto.auth.AuthLoginRequest;
import com.example.InaiProject.dto.auth.AuthRegisterRequest;
import com.example.InaiProject.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRegisterRequest authRegisterRequest);
    AuthResponse login(AuthLoginRequest authLoginRequest);
    // todo here will logic for "forgot-password"
}
