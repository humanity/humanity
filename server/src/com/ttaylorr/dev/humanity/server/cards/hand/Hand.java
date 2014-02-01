package com.ttaylorr.dev.humanity.server.cards.hand;

import java.util.LinkedList;
import java.util.List;

import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;

public class Hand {
	List<WhiteCard> cards;
	static int cardsPerHand;

	static {
		cardsPerHand = 7;
	}

	public Hand() {
		cards = new LinkedList<WhiteCard>();
	}

	/**
	 * Automatically draw all the cards that are needed based on the current number of cards in the player's hand.
	 */
	void autoDraw() {
		for (; cards.size() <= cardsPerHand;) {
			cards.add(Deck.getWhiteDeck().drawCard());
		}
	}

}
