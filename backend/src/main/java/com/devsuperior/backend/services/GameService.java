package com.devsuperior.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.backend.dto.GameDTO;
import com.devsuperior.backend.dto.GameMinDTO;
import com.devsuperior.backend.entities.Game;
import com.devsuperior.backend.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	// Garante que a operação com o banco de dados vai acontecer obedecendo os princípios das transações ACID
	@Transactional(readOnly = true) // Garante que toda a operação com o banco seja resolvida aqui e o readOnly = true garante que não haverá lock no banco
	public GameDTO findById(Long id) {
		Game entity = gameRepository.findById(id).get();
		return new GameDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		return result.stream().map(GameMinDTO::new).toList();
	}


}