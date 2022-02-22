package com.goose.services;

import java.util.ArrayList;

public class Turn {

	private Cards topCard;
	private Cards cardPlayed;
	private ArrayList<Hand> hands;
	private Cards previousCard;
	private int currentPlayer;
	private int turns;
	private int previousPlayer;
	private boolean reverse;
	private boolean draw;
	

	public Turn(Cards previousCard, ArrayList<Hand> hands, Cards topCard, Cards cardPlayed, int currentPlayer, int turns, int previousPlayer, boolean reverse, boolean draw) {
		super();
		this.previousCard = previousCard;
		this.hands = hands;
		this.topCard = topCard;
		this.cardPlayed = cardPlayed;
		this.currentPlayer = currentPlayer;
		this.turns = turns;
		this.previousPlayer = previousPlayer;
		this.reverse = reverse;
		this.draw = draw;
	}
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	public ArrayList<Hand> getHands() {
		return hands;
	}
	public Cards getTopCard() {
		return topCard;
	}
	public Cards getCardPlayed() {
		return cardPlayed;
	}
	public Cards getPreviousCard() {
		return previousCard;
	}
	public int getTurns() {
		return turns;
	}
	public int getPreviousPlayer() {
		return previousPlayer;
	}
	public boolean isReverse() {
		return reverse;
	}
	public boolean isDraw() {
		return draw;
	}
	
}
