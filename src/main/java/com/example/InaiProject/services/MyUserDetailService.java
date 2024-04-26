package com.example.InaiProject.services;


import com.example.InaiProject.dto.user.UserRequest;
import com.example.InaiProject.dto.user.UserResponse;

import java.util.List;

public interface MyUserDetailService {
    List<UserResponse> getAll();
    UserResponse findByEmail(String email);
    void updateByEmail(String email, UserRequest userRequest);
    void deleteByEmail(String email);
}
