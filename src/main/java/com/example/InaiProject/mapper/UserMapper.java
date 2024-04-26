package com.example.InaiProject.mapper;


import com.example.InaiProject.dto.user.UserRequest;
import com.example.InaiProject.dto.user.UserResponse;
import com.example.InaiProject.model.User;

import java.util.List;

public interface UserMapper {
    UserResponse toDto(User user);
    List<UserResponse> toDtoS(List<User> userList);
    User toDtoUser(User user, UserRequest userRequest);
}
