package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;

public interface UserService {


    boolean login(UserServiceModel userServiceModel);

    boolean usernameNotUnique(String username);

    void register(UserServiceModel userServiceModel);

    void logout();

    UserServiceModel findById(Long id);

    User findUserById(Long id);
}
