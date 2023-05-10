package com.devsuperior.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.backend.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{
    
}
