package com.example.spring.controller;

import com.example.spring.model.Game;
import com.example.spring.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("")
    public List<Game> findAllGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/{id}")
    public Optional<Game> findGameById(@PathVariable Long id) {
        return gameService.findGameById(id);
    }

    @GetMapping("/title/{title}")
    public Game findByTitle(@PathVariable String title) {
        return gameService.findByTitle(title);
    }

    @GetMapping("/price/{price}")
    public Game findGameByPrice(@PathVariable Double price) {
        return gameService.findGameByPrice(price);
    }

    @PostMapping("/create")
    public void createGame(@RequestBody Game game) {
        gameService.createGame(game);
    }

    @PutMapping("/{id}")
    public void updateGame(@PathVariable Long id, @RequestBody Game game) {
        gameService.updateGame(id, game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}
