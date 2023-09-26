package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.UserRole;
import com.example.pathfinder.model.enums.UserRoleEnum;

public interface UserRoleService {

    UserRole findByName(UserRoleEnum name);
}
