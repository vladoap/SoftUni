package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.entity.UserRole;
import com.example.pathfinder.model.enums.LevelEnum;
import com.example.pathfinder.model.enums.UserRoleEnum;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserRoleService;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    private CurrentUser currentUser;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleService userRoleService;
    private HttpServletRequest httpServletRequest;

    public UserServiceImpl(CurrentUser currentUser, ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleService userRoleService, HttpServletRequest httpServletRequest) {
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.httpServletRequest = httpServletRequest;
    }



    @Override
    public boolean login(UserServiceModel userServiceModel) {

        Optional<User> user = userRepository.findByUsername(userServiceModel.getUsername());

        if (user.isEmpty()) {
            logout();
            return false;
        }

        User loggedInUser = user.get();
        boolean successfullyLogged = passwordEncoder.matches(userServiceModel.getPassword(), loggedInUser.getPassword());

        if (successfullyLogged) {
            currentUser.setId(loggedInUser.getId());
            currentUser.setUsername(loggedInUser.getUsername());
            loggedInUser.getRoles().forEach(r -> currentUser.addRole(r));
        }

        return successfullyLogged;
    }

    @Override
    public void logout() {
        httpServletRequest.getSession().invalidate();
    }



    @Override
    public boolean usernameNotUnique(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        user.setLevel(LevelEnum.BEGINNER);


        int rndCountRoles = ThreadLocalRandom.current().nextInt(1, UserRoleEnum.values().length + 1);
        Set<UserRole> rndRoles = new HashSet<>();
        ThreadLocalRandom thread = ThreadLocalRandom.current();
        for (int i = 0; i < rndCountRoles; i++) {
            int currentRndRole = thread.nextInt(1, UserRoleEnum.values().length + 1);
            UserRole userRole = userRoleService.findByName(UserRoleEnum.values()[currentRndRole - 1]);
            rndRoles.add(userRole);

        }

        user.setRoles(rndRoles);

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findById(Long id) {
        return modelMapper.map(userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
