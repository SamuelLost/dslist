package com.devsuperior.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.backend.dto.GameListDTO;
import com.devsuperior.backend.entities.GameList;
import com.devsuperior.backend.projections.GameMinProjection;
import com.devsuperior.backend.repositories.GameListRepository;
import com.devsuperior.backend.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository	gameRepository;

	@Transactional(readOnly = true) // readOnly = true garante que não haverá lock no banco
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(GameListDTO::new).toList();
	}

	@Transactional
	public void move(Long listId, int sourceIndex, int targetIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(targetIndex, obj);
		
		int min = (sourceIndex < targetIndex) ? sourceIndex : targetIndex;
		int max = (sourceIndex > targetIndex) ? sourceIndex : targetIndex;

		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}

	}


}