package com.example.InaiProject.mapper.impl;

import com.example.InaiProject.config.JwtService;
import com.example.InaiProject.dto.auth.AuthRegisterRequest;
import com.example.InaiProject.dto.auth.AuthResponse;
import com.example.InaiProject.exception.CustomException;
import com.example.InaiProject.mapper.AuthMapper;
import com.example.InaiProject.model.Role;
import com.example.InaiProject.model.User;
import com.example.InaiProject.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthMapperImpl implements AuthMapper {
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    @Override
    public AuthResponse toDto(User user) {
        String token = jwtService.generateToken(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        return authResponse;
    }

    @Override
    public User toDtoUser(AuthRegisterRequest authRegisterRequest) {
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new CustomException("Role is not found", HttpStatus.NOT_FOUND));

        User user = new User();
        user.setPassword(encoder.encode(authRegisterRequest.getPassword()));
        user.setRole(role);
        user.setFirstName(authRegisterRequest.getFirstName());
        user.setLastName(authRegisterRequest.getLastName());
        user.setEmail(authRegisterRequest.getEmail());
        return user;
    }
}
