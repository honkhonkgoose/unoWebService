package com.goose.services;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Scanner;
//import java.util.List;
import java.util.Set;

public class UnoGameAPI {

private static HashMap<String, UnoGameAPI> games = new HashMap<>();
public static HashMap<String, UnoGameAPI> getGames() {
	return games;
}
int turns;
int currentPlayer;
int previousPlayer;
Cards tempCard;
Deck deck;
ArrayList<Hand> hands = new ArrayList<Hand>();
Cards card;
Cards previousCard;
boolean end = false;
Hand h = new Hand();
boolean nextTurn;
int numberofPlayers;
boolean reverse = false;
boolean draw = false;

	private UnoGameAPI() {
			System.out.println("Game Init...");
//			hands = new ArrayList<Hand>();//resets, so I'll leave it in the outer loop	
			deck = initializeGame(hands);
			tempCard  = deck.topDiscard();
			nextTurn = true;
			currentPlayer = 0;
			turns = 0;	
	}

	public static UnoGameAPI deleteGame(String gameName) {
		UnoGameAPI results = games.get(gameName);
		if (results != null) {
			games.remove(gameName);
		}
		return results;
	}
	
	// Factory method
	public static UnoGameAPI getGame(String gameName) {
		// use 'gameName' as a key in the games HashMap
		// if 'gameName' is not found, create a new game and add it to 'games' HashMap.  
		// return the new game.
		// if 'gameName' is found, return that game.	
		UnoGameAPI game = games.get(gameName);
		if (game == null) {
			game = new UnoGameAPI();
			games.put(gameName, game);
		}
		return game;
	}
	
	public static String[] getGameNames() {
		// use 'gameName' as a key in the games HashMap
		// if 'gameName' is not found, create a new game and add it to 'games' HashMap.  
		// return the new game.
		// if 'gameName' is found, return that game.
		Set<String> keys = games.keySet();
		return keys.toArray(new String[0]);
	}
	
	public boolean nextTurn() {
		// returns true as long as a winner has not been declared
		// returns false when the game has a winner
		// once winner has been determined, this method should return false each time.
		System.out.println("Progressing Game...");
		draw = false;
		if (nextTurn == true) {
			turns++;
			deck.replenish();// checks if deck needs to be replenished
			previousCard = tempCard; 
//			System.out.printf("Turn: %d\n", turns);
//			System.out.printf("Top Card: %s\n", tempCard);
//			System.out.printf("Player: %d %s \n", currentPlayer, hands.get(currentPlayer)); 			
			card = hands.get(currentPlayer).hasMatch(tempCard); // checking if player has a match
			if (card != null) { // if the player can play a card
//				System.out.println("Card Played:" + card); // what card they placed down
				tempCard = card;
				if (card.isSpecial(card) == true) { // checks if card is special
					switch (card.getValue()) {
					case SKIP:
						currentPlayer = getNextPlayer(currentPlayer); //she used i instead of currentPlayer in random places.
						break;
					case REVERSE:
						reverse = !reverse;
						break;
					case DRAWTWO:
						int nextPlayer = getNextPlayer(currentPlayer);
						for (int x = 0; x < 2; x++) {
							hands.get(nextPlayer).drawCard(deck.dealCard());
							deck.replenish();
						}
						break;
					case WILD:
						//hands.get(currentPlayer).colorCount(card);
						tempCard = h.colorCount(card);
//						System.out.println("Player has called the color: " + tempCard.getColor());
						break;
					case WILD_DRAWFOUR:
						tempCard = h.colorCount(card);
//						System.out.println("Player has called the color: " + tempCard.getColor());
						int wildNextPlayer = getNextPlayer(currentPlayer);
						for (int x = 0; x < 4; x++) {
							hands.get(wildNextPlayer).drawCard(deck.dealCard());
							deck.replenish();
						}
						break;
					default:
//						System.out.println("Hmm something went wrong with a special card");
						break;
					}
//					System.out.println("A special card has been played");
				}
				deck.addToDiscard(card); // takes the card the player played and puts it at the top of the discard deck
				if (hands.get(currentPlayer).isUno() == true) { // checks if player can call Uno
//					System.out.printf("\nPlayer %d calls UNO!\n", currentPlayer);
					
				}
				if (hands.get(currentPlayer).isWinner() == true) { // checks if player won
					nextTurn = winnerHouseKeeping(hands, currentPlayer);
				}
			}
				else { // if the player can't match a card they pick one up
					hands.get(currentPlayer).drawCard(deck.dealCard()); // player draws one card
//					System.out.printf("Player Drawing a Card: %d \n", currentPlayer); //display player number
//					System.out.println(hands.get(currentPlayer)); //displayers current players hand
					draw = true;
				}
				previousPlayer = currentPlayer;
				currentPlayer = getNextPlayer(currentPlayer); // getting the next player
//				System.out.println();
			
		}
		if (nextTurn == false) {
			
		}
		
		return nextTurn;
	}
	private Turn t; 
	public Turn getT() {
		return t;
	}


	public Turn getTurn() {
		// returns current turn
		// once the winner is declared, should return the last turn of the game
		if (nextTurn == true) {
			
			t = new Turn(previousCard, hands, tempCard, card, currentPlayer, turns, previousPlayer, reverse, draw);
			return t;
		}
		else {
			return t;
		}
	}		
	

//	private Scanner sc = new Scanner(System.in);

//	ArrayList<Integer> runningScore = new ArrayList<>();

	
	public void game() {        
		// Establishing the number of players.
		
	}
//	private boolean outterLoop() {
//		
//	}
//	private boolean innerLoop
//	}
	private boolean winnerHouseKeeping(ArrayList<Hand> hands, int currentPlayer) {
		nextTurn = false;
//		System.out.printf("\nPlayer %d Won\n", currentPlayer);
//		int winnerScore = finalScore(hands);
//		System.out.println("\nPlayer " + currentPlayer + " has scored " + winnerScore + " points!");
//		Integer prs = (runningScore.get(currentPlayer) + winnerScore);
//		runningScore.set(currentPlayer, prs);
//		if (prs >= 500) {
//			System.out.println("Player " + currentPlayer + " has won the whole tournament.");
//			end = true;
//			System.out.println("The final tally was:\n" + runningScore);
//		}
		return nextTurn;
	}
	private Deck initializeGame(ArrayList<Hand> hands) {
		// Prepping the deck
		Deck deck = new Deck();
		deck.populate();
//		System.out.println(deck);
		deck.shuffle();
//		System.out.println(deck);
		for (int i = 0; i < 4; i++) {
			hands.add(new Hand());
		}
		// adding cards into the hands of the players
		for (int i = 0; i < 7; i++) {
			for (Hand hand : hands) {
				hand.drawCard(deck.dealCard());
			}
		}
		// add a card to the discard to start it, draw until there is a normal card
		deck.discardPile();
		
//		System.out.println(hands);
		return deck;
	}
	private int getNextPlayer(int i) {

		if (reverse == false) {
			i++;
		} else if (reverse == true) {
			i--;
		}
		if (i > 3) { // making sure that i does not break out of array
			i = 0;
		}
		if (i < 0) { // making sure that i does not break out of array
			i = 3;
		}
		return i;
	}
	public int finalScore(ArrayList<Hand> hands) {
		int score = 0;
		for (int i = 0; i < hands.size(); i++) {//getting each hand in hands
			for (int h = 0; h < hands.get(i).getHand().size(); h++) {//getting each card in a hand
				Cards c = hands.get(i).getHand().get(h);//evaluates each card
				score = score + c.cardScore(c);//adds that card score to the overall score
			}
		}
		return score;
	}
	
	
}
