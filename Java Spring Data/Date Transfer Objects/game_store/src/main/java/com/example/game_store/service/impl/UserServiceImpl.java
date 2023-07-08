package com.example.game_store.service.impl;

import com.example.game_store.model.dto.UserLoginDto;
import com.example.game_store.model.dto.UserRegisterDto;
import com.example.game_store.model.entity.Game;
import com.example.game_store.model.entity.User;
import com.example.game_store.repository.UserRepository;
import com.example.game_store.service.GameService;
import com.example.game_store.service.UserService;
import com.example.game_store.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private User loggedUser;
    private GameService gameService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, GameService gameService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gameService = gameService;
    }


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Password don't match:");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violation = validationUtil.violation(userRegisterDto);
        if (!violation.isEmpty()) {
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violation = validationUtil.violation(userLoginDto);
        if (!violation.isEmpty()) {
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = userRepository.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()).orElse(null);

        if (user == null) {
            System.out.println("Incorrect username / password");
            return;
        }
        loggedUser = user;


    }

    @Override
    public void logoutUser() {
        if (loggedUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
            return;
        }
        loggedUser = null;
    }

    @Override
    public void showBoughtGamesByLoggedUser() {
        if (loggedUser == null) {
            System.out.println("No logged user!");
            return;
        }
        loggedUser.getGames()
                .stream()
                .map(Game::getTitle)
                .forEach(System.out::println);
    }

    @Override
    public void buyGame(String userName, long gameId) {
        User user = userRepository.findByFullName(userName);
        Game game = gameService.findGameById(gameId);

        if (game == null) {
            System.out.println("Game with such Id doesn't exist");
            return;
        }

        if (user == null) {
            System.out.println("User with such Id doesn't exist");
            return;
        }

        user.getGames().add(game);

        userRepository.save(user);

    }
}
