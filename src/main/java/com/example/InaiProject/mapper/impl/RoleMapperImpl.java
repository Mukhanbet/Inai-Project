package com.example.InaiProject.mapper.impl;

import com.example.InaiProject.dto.role.RoleResponse;
import com.example.InaiProject.mapper.RoleMapper;
import com.example.InaiProject.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleResponse toDto(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        return roleResponse;
    }

    @Override
    public List<RoleResponse> toDtoS(List<Role> roleList) {
        List<RoleResponse> roleResponseList = new ArrayList<>();
        for(Role role : roleList) {
            roleResponseList.add(toDto(role));
        }
        return roleResponseList;
    }
}
