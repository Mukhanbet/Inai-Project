package com.example.InaiProject.mapper;


import com.example.InaiProject.dto.auth.AuthRegisterRequest;
import com.example.InaiProject.dto.auth.AuthResponse;
import com.example.InaiProject.model.User;

public interface AuthMapper {
    AuthResponse toDto(User user);
    User toDtoUser(AuthRegisterRequest authRegisterRequest);
}
