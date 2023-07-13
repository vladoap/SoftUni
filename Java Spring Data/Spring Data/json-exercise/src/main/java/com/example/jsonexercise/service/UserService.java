package com.example.jsonexercise.service;

import com.example.jsonexercise.model.User;
import com.example.jsonexercise.repository.UserRepository;

import java.io.IOException;

public interface UserService {

    void seedUsers() throws IOException;

    User findRandomUser();

    void saveInJsonAllUsersWithProductsWithAtLeastOneSoldProduct() throws IOException;

    void saveInJsonAllUsersWithAtleastOneSoldProduct() throws IOException;
}
