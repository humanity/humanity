package com.ttaylorr.dev.humanity.server.cards.hand;

import java.util.LinkedList;
import java.util.List;

import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.player.Player;

public class Hand {
	List<WhiteCard> cards;
	static int cardsPerHand;
	private Player owner;

	static {
		cardsPerHand = 7;
	}

	public Hand(Player player) {
		cards = new LinkedList<WhiteCard>();
		this.owner = player;
	}

	/**
	 * Automatically draw all the cards that are needed based on the current number of cards in the player's hand.
	 */
	void autoDraw() {
		for (; cards.size() <= cardsPerHand;) {
			cards.add(Deck.getWhiteDeck().drawCard());
		}
	}

	public boolean isAccesible() {
		return !this.owner.isCardCsar();
	}

}
