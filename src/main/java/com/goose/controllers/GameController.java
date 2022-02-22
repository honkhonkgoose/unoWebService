package com.goose.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.goose.models.Game;
import com.goose.services.Turn;
import com.goose.services.UnoGameAPI;
@CrossOrigin(maxAge = 3600)
@RestController
public class GameController {

	@DeleteMapping("/games")
	Game deleteGame(@RequestBody Game game) {
		System.out.println("deleting " + game.getGameId());
		UnoGameAPI gameAPI = UnoGameAPI.deleteGame(game.getGameId());
		
		if (gameAPI != null) {
			System.out.println("success");
			return new Game(game.getGameId());
		}
		System.out.println("failure");
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find game.");
	}

	@PostMapping("/games")
	Turn postGame(@RequestBody Game game) {
		UnoGameAPI gameAPI = UnoGameAPI.getGame(game.getGameId());
		return gameAPI.getTurn();
	}
	
	@GetMapping("/games")
	List<Game> getGames(){
		String[] names = UnoGameAPI.getGameNames();
		List<Game> games = new ArrayList<>();
		for (String name : names) {
			games.add(new Game(name));
		}
		return games;
	}
	
	@GetMapping("/games/{name}")
	Turn postTurn(@RequestBody String name) {
		HashMap<String, UnoGameAPI> names = UnoGameAPI.getGames();
		UnoGameAPI trash = UnoGameAPI.getGame(name);
		if (names.containsValue(trash)) {
			UnoGameAPI game = UnoGameAPI.getGame(name);
			return game.getTurn();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource.");
	}
	
	@PutMapping ("/games")
	Turn getTurn(@RequestBody Game game) {
		UnoGameAPI gameAPI = UnoGameAPI.getGame(game.getGameId());
		Turn currentTurn = gameAPI.getTurn();
		if(gameAPI != null) {
			gameAPI.nextTurn();
			return gameAPI.getTurn();
		}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}
}
