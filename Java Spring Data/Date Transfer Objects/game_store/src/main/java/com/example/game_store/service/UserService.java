package com.example.game_store.service;

import com.example.game_store.model.dto.UserLoginDto;
import com.example.game_store.model.dto.UserRegisterDto;

public interface UserService {

     void registerUser(UserRegisterDto userRegisterDto);

     void loginUser(UserLoginDto userLoginDto);

     void logoutUser();

    void showBoughtGamesByLoggedUser();

    void buyGame(String userName, long gameId);

}
