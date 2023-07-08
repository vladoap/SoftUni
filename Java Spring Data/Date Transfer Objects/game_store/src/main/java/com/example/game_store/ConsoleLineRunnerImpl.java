package com.example.game_store;

import com.example.game_store.model.dto.GameAddDto;
import com.example.game_store.model.dto.UserLoginDto;
import com.example.game_store.model.dto.UserRegisterDto;
import com.example.game_store.service.GameService;
import com.example.game_store.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ConsoleLineRunnerImpl implements CommandLineRunner {

    private BufferedReader bufferedReader;
    private UserService userService;
    private GameService gameService;

    public ConsoleLineRunnerImpl(BufferedReader bufferedReader, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.userService = userService;
        this.gameService = gameService;
    }


    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter command:");
            String[] commands = bufferedReader.readLine().split("\\|");



            switch (commands[0]) {
                case "RegisterUser" -> userService.registerUser(new UserRegisterDto(commands[1], commands[2], commands[3], commands[4]));
                case "LoginUser" -> userService.loginUser(new UserLoginDto(commands[1], commands[2]));
                case "Logout" -> userService.logoutUser();
                case "AddGame" -> gameService.addGame(new GameAddDto(commands[1], commands[4], commands[5],
                        Double.parseDouble(commands[3]), new BigDecimal(commands[2]), commands[6],
                        commands[7]));
                case "EditGame" -> {
                    BigDecimal price = new BigDecimal(commands[2].split("=")[1]);
                    Double size = Double.parseDouble(commands[3].split("=")[1]);
                    gameService.editGame(Long.parseLong(commands[1]
                    ), price, size);
                }
                case "DeleteGame" -> gameService.deleteGame(Long.parseLong(commands[1]));
                case "AllGames" -> gameService.showAllGames();
                case "DetailGame" -> gameService.showDetailsForGame(commands[1]);
                case "OwnedGames" -> userService.showBoughtGamesByLoggedUser();
                case "BuyGame" -> userService.buyGame(commands[1], Long.parseLong(commands[2]));
                }
            }





        }
    }

