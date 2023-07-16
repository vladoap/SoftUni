package com.example.productshopdatabase.service.impl;

import com.example.productshopdatabase.model.dto.*;
import com.example.productshopdatabase.model.entity.User;
import com.example.productshopdatabase.repository.UserRepository;
import com.example.productshopdatabase.service.UserService;
import com.example.productshopdatabase.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

private final UserRepository userRepository;
private final ValidationUtil validationUtil;
private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {

        users
                .stream()
                .filter(validationUtil::isValid)
                .map(user -> modelMapper.map(user, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Override
    public User getRandomUser() {
        long rndId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return userRepository.findById(rndId).orElse(null);
    }

    @Override
    public UserWIthProductsRootDto findAllUsersWithSoldProducts() {
        UserWIthProductsRootDto userWIthProductsRootDto = new UserWIthProductsRootDto();

        userWIthProductsRootDto
                .setUsers(userRepository.findAllUsersWithSoldProductsOrderedByLastNameAndFirstName()
                        .stream()
                        .filter(user -> user.getSoldProducts().removeIf(product -> product.getBuyer() == null))
                        .map(user -> modelMapper.map(user, UserWithAgeWithSoldProductsDto.class))
                        .collect(Collectors.toList()));


        return userWIthProductsRootDto;

        //TODO: how to do the automatically mapping with nested classes.
    }

    @Override
    public UserWIthProductsRootDto findAllUsersWithSoldProductsOrderedByCountProducts() {
        UserWIthProductsRootDto userWIthProductsRootDto = new UserWIthProductsRootDto();



        userWIthProductsRootDto.setUsers(userRepository
                .findAllUsersWithAtLeastOneProductSoldOrderByProductsCount()
                .stream()
                .map(user -> {
                    UserWithAgeWithSoldProductsDto userDto = modelMapper.map(user, UserWithAgeWithSoldProductsDto.class);
                    if (userDto.getFirstName() == null) {
                        userDto.setFirstName("");
                    }


                    return userDto;
                })
                .collect(Collectors.toList()));

        return userWIthProductsRootDto;
    }

}
