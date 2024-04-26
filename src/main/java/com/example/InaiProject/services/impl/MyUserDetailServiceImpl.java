package com.example.InaiProject.services.impl;

import com.example.InaiProject.dto.user.UserRequest;
import com.example.InaiProject.dto.user.UserResponse;
import com.example.InaiProject.exception.CustomException;
import com.example.InaiProject.mapper.UserMapper;
import com.example.InaiProject.model.User;
import com.example.InaiProject.repository.UserRepository;
import com.example.InaiProject.services.MyUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MyUserDetailServiceImpl implements MyUserDetailService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserResponse> getAll() {
        return userMapper.toDtoS(userRepository.findAll());
    }

    @Override
    public UserResponse findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void updateByEmail(String email, UserRequest userRequest) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new CustomException("User with this email has already exist", HttpStatus.ALREADY_REPORTED);
        }
        userRepository.save(userMapper.toDtoUser(user, userRequest));
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.delete(userRepository.findByEmail(email).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND)));
    }
}
