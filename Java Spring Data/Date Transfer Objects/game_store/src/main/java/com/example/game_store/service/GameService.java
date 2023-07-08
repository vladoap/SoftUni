package com.example.game_store.service;

import com.example.game_store.model.dto.GameAddDto;
import com.example.game_store.model.entity.Game;

import java.math.BigDecimal;

public interface GameService {

    void addGame(GameAddDto gameAddDto);

    void editGame(Long id, BigDecimal price, Double size);

    void deleteGame(long id);

    void showAllGames();

    void showDetailsForGame(String title);

    Game findGameById(Long id);
}
