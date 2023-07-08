package com.example.game_store.service.impl;

import com.example.game_store.model.dto.GameAddDto;
import com.example.game_store.model.entity.Game;
import com.example.game_store.repository.GameRepository;
import com.example.game_store.service.GameService;
import com.example.game_store.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {


    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private GameRepository gameRepository;

    public GameServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper, GameRepository gameRepository) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
    }


    @Override
    public void addGame(GameAddDto gameAddDto) {
        Set<ConstraintViolation<GameAddDto>> violation = validationUtil.violation(gameAddDto);
        if (!violation.isEmpty()) {
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);
        game.setReleaseDate(LocalDate.parse(gameAddDto.getReleaseDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        gameRepository.save(game);

        System.out.println("Added " + game.getTitle());
    }

    @Override
    public void editGame(Long id, BigDecimal price, Double size) {
        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            System.out.println("Id is not valid");
            return;
        }

        game.setSize(size);
        game.setPrice(price);

        gameRepository.save(game);
    }

    @Override
    public void deleteGame(long id) {
        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            System.out.println("Id is not valid");
            return;
        }
        gameRepository.delete(game);
    }

    @Override
    public void showAllGames() {
        List<Game> games = gameRepository.findAll();
        games
                .stream()
                .map(game -> String.format("%s %f", game.getTitle(), game.getPrice()))
                .forEach(System.out::println);
    }

    @Override
    public void showDetailsForGame(String title) {
        Game game = gameRepository.findByTitle(title);

        if (game == null) {
            System.out.println("Game with such title doesn't exist");
            return;
        }

        System.out.printf("Title: %s\n" +
                "Price: %.2f \n" +
                "Description: %s \n" +
                "Release date: %s\n",
                game.getTitle(), game.getPrice(), game.getDescription(), game.getReleaseDate());
    }

    public Game findGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }
}
