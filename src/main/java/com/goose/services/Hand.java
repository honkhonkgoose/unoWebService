package com.goose.services;
import java.util.ArrayList;
import java.util.Collections;

public class Hand {
	// drawing cards
	public void drawCard(Cards card) {
		hand.add(card);
	}

	// checking if the player has a card to put down
	public Cards hasMatch(Cards cardToMatch) {
		Cards match = null;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).isMatch(cardToMatch) == true) {
				match = hand.get(i);
				hand.remove(i);
				return match;
			}
		}
		return match;
	}
	//all sorts of reprogramming
	public Cards colorCount(Cards wildCard) { // identifying which color the player has the most of
		Cards newColor = new Cards(CardsValue.WILD, CardsColor.YELLOW);

		if (hand.size() > 0) {
			int r = 0, b = 0, y = 0, g = 0, most;
			for (int i = 0; i < hand.size(); i++) {
				switch (hand.get(i).getColor()) {
				case RED:
					r++;
					break;
				case BLUE:
					b++;
					break;
				case YELLOW:
					y++;
					break;
				case GREEN:
					g++;
					break;
				default:
					break;
				}
			}
			ArrayList<Integer> sortColor = new ArrayList<Integer>();
			sortColor.add(r);
			sortColor.add(b);
			sortColor.add(g);
			sortColor.add(y);
			Collections.sort(sortColor);
			most = sortColor.get(3);
			// newColor returns the wild card with the changed color value
			if (most == r) {
				newColor = new Cards(CardsValue.WILD, CardsColor.RED);
			}
			if (most == b) {
				newColor = new Cards(CardsValue.WILD, CardsColor.BLUE);
			}
			if (most == g) {
				newColor = new Cards(CardsValue.WILD, CardsColor.GREEN);
			}
		}

		return newColor;
	}

	// checks for Uno
	public boolean isUno() {
		if (hand.size() == 1) {
			return true;
		}
		return false;
	}

	// checks for win condition
	public boolean isWinner() {
		if (hand.size() == 0) {
			return true;
		}
		return false;
	}

	
	public ArrayList<Cards> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Cards> hand) {
		this.hand = hand;
	}

	private ArrayList<Cards> hand = new ArrayList<Cards>();

	@Override
	public String toString() {
		return "Hand " + hand;
	}

}
