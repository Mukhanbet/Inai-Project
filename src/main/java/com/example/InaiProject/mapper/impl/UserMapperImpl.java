package com.example.InaiProject.mapper.impl;

import com.example.InaiProject.dto.user.UserRequest;
import com.example.InaiProject.dto.user.UserResponse;
import com.example.InaiProject.mapper.UserMapper;
import com.example.InaiProject.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }

    @Override
    public List<UserResponse> toDtoS(List<User> userList) {
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : userList) {
            userResponses.add(toDto(user));
        }
        return userResponses;
    }

    @Override
    public User toDtoUser(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }
}
