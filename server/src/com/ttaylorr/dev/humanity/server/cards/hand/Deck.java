package com.ttaylorr.dev.humanity.server.cards.hand;

import java.util.Collections;
import java.util.LinkedList;

import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.core.Card;
import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;

public class Deck<C extends Card> {

	private LinkedList<C> onDeck;

	public Deck() {
		onDeck = (LinkedList<C>) Collections.synchronizedList(new LinkedList<C>());
		// pass this to a factory class that creates all the cards for the entire
	}


	public synchronized C drawCard() {
		return onDeck.pop();
	}

	public synchronized void releaseCard(C card) {
		onDeck.offer(card);
	}

	static Deck<BlackCard> blackDeck;
	static Deck<WhiteCard> whiteDeck;

	static {
		blackDeck = new Deck<>();
		whiteDeck = new Deck<>();
	}

	public static Deck<BlackCard> getBlackDeck() {
		return blackDeck;
	}

	public static Deck<WhiteCard> getWhiteDeck() {
		return whiteDeck;
	}

}
