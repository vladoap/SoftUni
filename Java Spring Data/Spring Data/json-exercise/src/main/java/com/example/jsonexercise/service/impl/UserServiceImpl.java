package com.example.jsonexercise.service.impl;

import com.example.jsonexercise.model.User;
import com.example.jsonexercise.model.dto.UserSeedDto;
import com.example.jsonexercise.model.dto.UserWithSoldProductsDto;
import com.example.jsonexercise.model.dto.UsersWithSoldProductsFnLnADto;
import com.example.jsonexercise.repository.UserRepository;
import com.example.jsonexercise.service.UserService;
import com.example.jsonexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.example.jsonexercise.constants.GlobalConstants.RESOURCES_ROOT_PATH;
import static com.example.jsonexercise.constants.GlobalConstants.RESOURCES_ROOT_PATH_OUT;

@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_NAME = "users.json";
    private static final String USERS_SOLD_PRODUCTS = "users-sold-products.json";
    private static final String USERS_AND_PRODUCTS = "users-and-products.json";
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedUsers() throws IOException {

        if (userRepository.count() > 0) {
            return;
        }

        String fileContent = Files
                .readString(Path.of(RESOURCES_ROOT_PATH + USERS_FILE_NAME));

        UserSeedDto[] userSeedDto = gson.fromJson(fileContent, UserSeedDto[].class);


        Arrays.stream(userSeedDto)
                .filter(validationUtil::isValid)
                .map(user -> modelMapper.map(user, User.class))
                .forEach(userRepository::save);

    }

    @Override
    public User findRandomUser() {
        long countUsers = userRepository.count();
        long rndUserId = ThreadLocalRandom.current().nextLong(1, countUsers + 1);

        return userRepository.findById(rndUserId).orElse(null);
    }

    @Override
    public void saveInJsonAllUsersWithProductsWithAtLeastOneSoldProduct() throws IOException {
        List<User> users = userRepository.findAllUsersWithAtLeastOneSoldProductOrderByLastNameAndFirstName();

        List<UserWithSoldProductsDto> usersDto = users.stream()
                .map(user -> {
                    user.getSoldProducts().removeIf(p -> p.getBuyer() == null);
                    return user;
                })
                .map(user -> modelMapper.map(user, UserWithSoldProductsDto.class))
                .collect(Collectors.toList());


        FileWriter fileWriter = new FileWriter(RESOURCES_ROOT_PATH_OUT + USERS_SOLD_PRODUCTS);
        gson.toJson(usersDto, fileWriter);

        fileWriter.flush();



    }

    @Override
    public void saveInJsonAllUsersWithAtleastOneSoldProduct() throws IOException {
        List<User> result = userRepository.findAllByUsersWithAtLeastOneSoldProductOrderedByCountOfProducts();

        List<UsersWithSoldProductsFnLnADto> usersDto = result.stream()
                .map(user -> {
                    user.getSoldProducts().removeIf(pr -> pr.getBuyer() == null);
                    return user;
                })
                .map(user -> modelMapper.map(user, UsersWithSoldProductsFnLnADto.class))
                .collect(Collectors.toList());

        String content = gson.toJson(usersDto);

        Files.write(Path.of(RESOURCES_ROOT_PATH_OUT + USERS_AND_PRODUCTS), Collections.singleton(content));
    }


}

