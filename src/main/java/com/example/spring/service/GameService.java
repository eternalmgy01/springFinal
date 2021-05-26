package com.example.spring.service;

import com.example.spring.DAO.GameDAO;
import com.example.spring.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameDAO gameDAO;

    public List<Game> findAllGames() {
        return gameDAO.findAll();
    }

    public Optional<Game> findGameById(Long id) {
        return gameDAO.findById(id);
    }

    public Game findByTitle(String title) {
        return gameDAO.findGameByTitle(title);
    }

    public Game findGameByPrice(Double price) {
        return gameDAO.findGameByPrice(price);
    }

    public void createGame(Game game) {
        gameDAO.save(game);
    }

    public void updateGame(Long id, Game game) {
        Game gameDb = gameDAO.findById(id).orElse(null);
        if (gameDb != null) {
            gameDb.setDescription(game.getDescription());
            gameDb.setPrice(game.getPrice());
            gameDAO.saveAndFlush(gameDb);
        }
    }

    public void deleteGame(Long id) {
        gameDAO.deleteById(id);
    }
}
