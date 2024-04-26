package com.example.InaiProject.mapper;


import com.example.InaiProject.dto.role.RoleResponse;
import com.example.InaiProject.model.Role;

import java.util.List;

public interface RoleMapper {
    RoleResponse toDto(Role role);
    List<RoleResponse> toDtoS(List<Role> role);
}
