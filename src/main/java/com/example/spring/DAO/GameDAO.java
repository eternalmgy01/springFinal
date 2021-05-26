package com.example.spring.DAO;

import com.example.spring.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDAO extends JpaRepository<Game, Long> {
    Game findGameByTitle(String title);
    Game findGameByPrice(Double price);
}
