package com.goose.models;

import org.springframework.stereotype.Component;

@Component
public class Game {

	private String gameId;
	public Game() {
		super();
	}
	public Game(String gameId) {
		super();
		this.gameId = gameId;
	}
	public String getGameId() {
		return gameId;
	}

}
