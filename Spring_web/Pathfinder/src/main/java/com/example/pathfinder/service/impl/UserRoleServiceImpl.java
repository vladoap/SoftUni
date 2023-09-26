package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.UserRole;
import com.example.pathfinder.model.enums.UserRoleEnum;
import com.example.pathfinder.repository.UserRoleRepository;
import com.example.pathfinder.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole findByName(UserRoleEnum name) {
        return userRoleRepository.findByName(name).orElse(null);
    }
}
