package com.devsuperior.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.backend.dto.GameDTO;
import com.devsuperior.backend.dto.GameMinDTO;
import com.devsuperior.backend.services.GameService;

@RestController
@RequestMapping(value = "/api/games")
public class GameController {
    
    @Autowired
    private GameService gameService;

    @GetMapping(value = "/{id}")
    public GameDTO findById(@PathVariable Long id) {
        return gameService.findById(id);
    }
    
    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }
}
