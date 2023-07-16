package com.example.productshopdatabase.service;

import com.example.productshopdatabase.model.dto.UserSeedDto;
import com.example.productshopdatabase.model.dto.UserWIthProductsRootDto;
import com.example.productshopdatabase.model.entity.User;

import java.util.List;

public interface UserService {


    void seedUsers(List<UserSeedDto> users);

     long getCount();

     User getRandomUser();

    UserWIthProductsRootDto findAllUsersWithSoldProducts();

    UserWIthProductsRootDto findAllUsersWithSoldProductsOrderedByCountProducts();
}
